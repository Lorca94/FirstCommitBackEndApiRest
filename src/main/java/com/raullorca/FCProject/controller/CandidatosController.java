package com.raullorca.FCProject.controller;

import com.raullorca.FCProject.entity.Candidato;
import com.raullorca.FCProject.entity.Etiqueta;
import com.raullorca.FCProject.repository.CandidatosRepository;
import com.raullorca.FCProject.security.payload.AddEtiquetRequest;
import com.raullorca.FCProject.security.payload.MessageResponse;
import com.raullorca.FCProject.security.payload.RegisterCandidatoRequest;
import com.raullorca.FCProject.service.CandidatosService;
import com.raullorca.FCProject.service.EtiquetaService;
import com.raullorca.FCProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/candidatos")
public class CandidatosController {

    //Servicios que se van a utilizar
    @Autowired
    private final UserService userService;
    @Autowired
    private final CandidatosService candidatosService;
    @Autowired
    private final EtiquetaService etiquetaService;


    //Constructor
    public CandidatosController(UserService userService, CandidatosRepository candidatosRepository,
                                CandidatosService candidatosService, EtiquetaService etiquetaService){
        this.userService = userService;
        this.candidatosService=candidatosService;
        this.etiquetaService=etiquetaService;

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
                    //Se crea usuario
                    Candidato candidato = new Candidato(registerCandidato.getNombreCompleto(),
                            registerCandidato.getEmail(),registerCandidato.getTelefono(),
                            registerCandidato.getCiudad(), registerCandidato.getPais(),
                            registerCandidato.getPresencialidad(), registerCandidato.getTraslado(),
                            registerCandidato.getImagen(), registerCandidato.getCurriculum());
                    try{
                        //Se añade la relación candidato/usuario
                        candidatosService.addUserToCandidato(registerCandidato.getAppUserRel(), candidato);
                        //Se comprueba si la etiqueta ya existe
                        if(etiquetaService.existsByEtiqueta(registerCandidato.getEtiqueta())){
                            //Se trae la etiqueta y se añade la relación candidato/etiqueta
                            Etiqueta etiqueta = etiquetaService.findByEtiqueta(registerCandidato.getEtiqueta());
                            candidato.addEtiqueta(etiqueta);
                        } else {
                            //En caso de no existir se crea la etiqueta, y se añade a la BBDD
                            Etiqueta etiqueta = new Etiqueta(registerCandidato.getEtiqueta());
                            etiquetaService.createEtiqueta(etiqueta);
                            //Se añade la etiqueta usando el dato guardado en la BBDD
                            candidato.addEtiqueta(etiquetaService.findByEtiqueta(etiqueta.getEtiqueta()));
                        }
                        //Se guarda el candidato
                        candidatosService.saveCandidato(candidato);
                        return ResponseEntity.ok().body(new MessageResponse("Candidato creado, con éxito"));

                    } catch (Exception e){
                        throw new Exception("Error al crear el candidato" + e);
                    }

            }
        }
        //Candidato vacío
        return ResponseEntity.badRequest().body(new MessageResponse("Candidato vacío"));

    }

    /**
     * Actualizar candidato
     * @param candidato se le pasa un candidato
     * @return ResponseEntity con el candidato actualizado
     */
    @PutMapping("/")
    public ResponseEntity<Candidato> update( Candidato candidato ){
        // Se comprueba que la id no esté vacía y se actualizan los datos del candidato
        if ( candidato.getId() == null){
            return ResponseEntity.badRequest().build();
        }
        candidatosService.saveCandidato(candidato);
        return ResponseEntity.ok(candidato);
    }

    /**
     * Añadir etiqueta a un candidato ya creado
     * @param candidato se pasan los datos del un candidato
     * @param addEtiquetRequest se utiliza etiqueta Request para pasar la etiqueta añadir
     * @return devuelve una response Entity de tipo ResponseMessage
     * @throws Exception en caso de fallo en la actualización del candidato lanzará un error
     */
    @PostMapping("/new/etiqueta")
    public ResponseEntity<MessageResponse> addEtiqueta(Candidato candidato, @RequestBody AddEtiquetRequest addEtiquetRequest) throws Exception {
        // Se comprueba que la id no esté vacía y se actualizan los datos del candidato
        if (candidato.getId() == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("No se ha encontrado una ID válida para añadir la etiqueta"));
        }
        try {
            //Se trae el candidato de la BBDD para verífica
            if(!etiquetaService.existsByEtiqueta(addEtiquetRequest.getEtiqueta())){
                Etiqueta etiqueta = new Etiqueta(addEtiquetRequest.getEtiqueta());
                etiquetaService.createEtiqueta(etiqueta);
            }
            candidato.addEtiqueta(etiquetaService.findByEtiqueta(addEtiquetRequest.getEtiqueta()));
            candidatosService.saveCandidato(candidato);
            return ResponseEntity.ok().body(new MessageResponse("Etiqueta añadida"));
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @DeleteMapping("/candidato/{id}")
    public ResponseEntity<Candidato> deleteCandidato(@PathVariable Long id){
        candidatosService.deleteById(id);
        return ResponseEntity.noContent().build();
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
