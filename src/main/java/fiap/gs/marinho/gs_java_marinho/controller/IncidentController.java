package fiap.gs.marinho.gs_java_marinho.controller;


import fiap.gs.marinho.gs_java_marinho.entity.Incident;
import fiap.gs.marinho.gs_java_marinho.service.IncidentService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/incidents")
//@Api(tags = "Incident")
public class IncidentController {

    private IncidentService incidentService;

    public IncidentController(IncidentService incidentService){
        this.incidentService = incidentService;
    }

    @GetMapping
   // @ApiOperation(value = "Listar incidentes")
    public List<EntityModel<Incident>> listIncidents(){
        List <Incident> incidents = incidentService.listIncident();
        return incidents.stream()
                .map(incident -> EntityModel.of(incident,
                        WebMvcLinkBuilder.linkTo(methodOn(IncidentController.class).findbyIncident(incident.getId())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(methodOn(IncidentController.class).listIncidents()).withRel("incidents")
                        ))
                .collect(Collectors.toList());

    }

    @GetMapping("/{id}")
   // @ApiOperation(value = "Buscar incidente por ID")
    public ResponseEntity<EntityModel<Incident>> findbyIncident(@PathVariable Long id){
        Optional<Incident> incidentOptional = Optional.ofNullable(incidentService.findbyIncident(id));
        if (incidentOptional.isPresent()){
            Incident incident = incidentOptional.get();
            EntityModel<Incident> resource = EntityModel.of(incident);
            resource.add(WebMvcLinkBuilder.linkTo(methodOn(IncidentController.class).findbyIncident(id)).withSelfRel());
            resource.add(WebMvcLinkBuilder.linkTo(methodOn(IncidentController.class).listIncidents()).withRel("incidents"));
            return ResponseEntity.ok(resource);}
            else {
                return ResponseEntity.notFound().build();
            }


    }
//    @GetMapping("/incident/{incident}")
//    public Optional<Incident> findIncident(String incident) {
//        return incidentService.findIncident(incident);
//    }
    @PostMapping
   // @ApiOperation(value = "Criar incidente")
    public ResponseEntity<EntityModel<Incident>> createIncident(@RequestParam Long userId, @RequestBody Incident incident){
        Incident createdIncident = incidentService.createIncident(userId, incident);
        return ResponseEntity.ok().body(EntityModel.of(createdIncident,
                WebMvcLinkBuilder.linkTo(methodOn(IncidentController.class).findbyIncident(createdIncident.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(methodOn(IncidentController.class).listIncidents()).withRel("incidents")
        ));
    }

    @PutMapping
 //   @ApiOperation(value = "Atualizar incidente")
    public ResponseEntity<EntityModel<Incident>>updateIncident(@RequestBody Incident incident){
        Incident incidents = incidentService.updateIncident(incident);
        return ResponseEntity.ok().body(EntityModel.of(incident,
                WebMvcLinkBuilder.linkTo(methodOn(IncidentController.class).findbyIncident(incident.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(methodOn(IncidentController.class).listIncidents()).withRel("incidents")
        ));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Incident> patchIncident(@PathVariable Long id, @RequestBody Incident incident) {
        Incident updatedIncident = incidentService.updateIncident(incident);
        return ResponseEntity.ok(updatedIncident);
    }



    @DeleteMapping("/{id}")
    //@ApiOperation(value = "Deletar incidente")
    public ResponseEntity<EntityModel<Incident>> deleteIncident(@PathVariable Long id){
        incidentService.deleteIncident(id);
        List<Incident> incidents = incidentService.listIncident();
        List<EntityModel<Incident>> resources = incidents.stream()
                .map(incident -> EntityModel.of(incident,
                        WebMvcLinkBuilder.linkTo(methodOn(IncidentController.class).findbyIncident(incident.getId())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(methodOn(IncidentController.class).listIncidents()).withRel("incidents")
                ))
                .collect(Collectors.toList());
                return ResponseEntity.ok().body(resources.get(0));


    }

}
