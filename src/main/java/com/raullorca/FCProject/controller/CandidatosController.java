package com.raullorca.FCProject.controller;

import com.raullorca.FCProject.entity.Candidato;
import com.raullorca.FCProject.entity.Etiqueta;
import com.raullorca.FCProject.entity.RelatedCanEt;
import com.raullorca.FCProject.repository.CandidatosRepository;
import com.raullorca.FCProject.repository.RelatedCanEtRepository;
import com.raullorca.FCProject.security.payload.MessageResponse;
import com.raullorca.FCProject.security.payload.RegisterCandidatoRequest;
import com.raullorca.FCProject.service.CandidatosService;
import com.raullorca.FCProject.service.EtiquetaService;
import com.raullorca.FCProject.service.RelatedCanEtService;
import com.raullorca.FCProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/candidatos")
public class CandidatosController {

    //Servicios que se van a necesitar
    @Autowired
    private final UserService userService;
    @Autowired
    private final CandidatosService candidatosService;
    @Autowired
    private final EtiquetaService etiquetaService;
    @Autowired
    private final RelatedCanEtService relationService;


    //Constructor
    public CandidatosController(UserService userService, CandidatosRepository candidatosRepository,
                                CandidatosService candidatosService, EtiquetaService etiquetaService,
                                RelatedCanEtService relationService){
        this.userService = userService;
        this.candidatosService=candidatosService;
        this.etiquetaService=etiquetaService;
        this.relationService=relationService;

    }

    /**
     * Devuelve todos los candidatos
     * @return List de candidatos
     */
    @GetMapping("/")
    public ResponseEntity<List<Candidato>>getCandidatos(){
        List<Candidato> candidatos = candidatosService.findAll();
        return ResponseEntity.ok().body(candidatosService.getCandidatos());
    }

    /**
     * Devuelve un candidato según su id
     * @param id del candidato a buscar
     * @return candidato
     */
    @GetMapping("/{id}")
    public ResponseEntity<Candidato> findById(@PathVariable Long id) {
        Optional<Candidato> candOpt = candidatosService.findById(id);
        return candOpt
                .map(
                        candidato -> ResponseEntity.ok(candidato))
                .orElseGet(
                        () -> ResponseEntity.notFound().build()
                );
    }


    /**
     * Registra un nuevo candidato, una etiqueta y la relación entre ambos.
     * @param registerCandidato es un payload de candidato
     * la validación de los parametros de candidato se realizan en el metodo candidatoValidation
     * @return Response Entity con error en caso de fallo y ok en caso de poder insertar en la BBDD
     * @throws Exception evitar errores al crear el usuario
     */
    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@RequestBody RegisterCandidatoRequest registerCandidato) throws Exception {
        //Condición para no recibir un payload vacío
        if (registerCandidato != null){
            //Switch sobre los valores que nos va a devolver candidatoValidation
            switch (candidatoValidation(registerCandidato)){
                //Datos de candidato incompletos
                case 1 :
                    return ResponseEntity
                            .badRequest()
                            .body(new MessageResponse("Error: Datos de candidato incompletos."));
                //Usuario no registrado
                case 2:
                    return ResponseEntity
                            .badRequest()
                            .body(new MessageResponse("Error: Usuario no encontrado, se necesita un usuario registrado para añadir un nuevo candidato"));
                //Email de candidato ya registrado
                case 3:
                    return ResponseEntity
                            .badRequest()
                            .body(new MessageResponse("Error: El email que estás intentando registrar ya existe."));
                // OK
                case 4:
                    //Creamos usuario y etiqueta
                    Candidato candidato = new Candidato(registerCandidato.getNombreCompleto(), registerCandidato.getEmail(),
                            registerCandidato.getTelefono(), registerCandidato.getCiudad(), registerCandidato.getPais(),
                            registerCandidato.getPresencialidad(),registerCandidato.getTraslado(),registerCandidato.getImagen(),
                            registerCandidato.getCurriculum());
                    Etiqueta etiqueta = new Etiqueta(registerCandidato.getEtiqueta());
                    try{
                        //Se guarda en BBDD el nuevo candidato y la nueva etiqueta
                        etiquetaService.createEtiqueta(etiqueta);
                        candidatosService.saveCandidato(registerCandidato.getAppUserRel(),candidato);

                        //Se añade el id, para evitar los problemas con optional, se tomará el último valor del tamaño de la tabla en BBDD
                        candidato.setId((long) candidatosService.findAll().size());
                        etiqueta.setId((long) etiquetaService.findAll().size());

                        //Se crea la relación
                        relationService.createRelation(candidato,etiqueta);

                        return ResponseEntity.ok().body(new MessageResponse("Candidato creado, con éxito"));

                    } catch (Exception e){
                        throw new Exception("Error al crear el usuario" + e);
                    }

            }
        }
        //Candidato vacío
        return ResponseEntity.badRequest().body(new MessageResponse("Candidato vacío"));

    }

    /**
     * Muestra etiquetas del candidato, así como el candidato y el usuario que lo ha creado.
     * @param id de la relacion entre tablas
     * @return Devuelve el las etiquetas del candidato, así como el candidato y el usuario que lo ha creado.
     */
   @GetMapping("/lenguajes/{id}")
   public ResponseEntity<RelatedCanEt> findRelacion(@PathVariable Long id){
        Optional<RelatedCanEt> relacion= relationService.findById(id);
       return relacion
               .map(
                       relatedCanEt -> ResponseEntity.ok(relatedCanEt))
               .orElseGet(
                       () -> ResponseEntity.notFound().build()
               );
   }

    /**
     * Se comprobarán los datos de candidato pasados por el payload y devolverá un valor entero para poder gestionar
     * un response entity adecuado
     * @param candidatoValidate payload de candidato
     * @return number int
     */
    public int candidatoValidation(RegisterCandidatoRequest candidatoValidate){

        if(candidatoValidate.getNombreCompleto()==null || candidatoValidate.getEmail()==null
                || candidatoValidate.getTelefono()==null  || candidatoValidate.getCiudad()==null
                || candidatoValidate.getPais()==null || candidatoValidate.getPresencialidad()==null
                || candidatoValidate.getTraslado()==null || candidatoValidate.getEtiqueta()==null) {
            return 1;
        }

        if(!userService.existsByEmail(candidatoValidate.getAppUserRel())) {
            return 2;
        }

        if(candidatosService.existsByEmail(candidatoValidate.getEmail())){
            return 3;
        }

        return 4;
    }
}
