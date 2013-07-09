package io.brooklyn.camp.dto;

import io.brooklyn.camp.rest.resource.ApidocRestResource;
import io.brooklyn.camp.rest.util.DtoFactory;
import io.brooklyn.camp.spi.ApplicationComponent;
import io.brooklyn.camp.spi.ApplicationComponentTemplate;
import io.brooklyn.camp.spi.Assembly;
import io.brooklyn.camp.spi.AssemblyTemplate;
import io.brooklyn.camp.spi.Link;
import io.brooklyn.camp.spi.PlatformComponent;
import io.brooklyn.camp.spi.PlatformComponentTemplate;
import io.brooklyn.camp.spi.PlatformRootSummary;

import java.util.ArrayList;
import java.util.List;

public class PlatformDto extends ResourceDto {

    // defined as a constant so can be used in Swagger REST API annotations
    public static final String CLASS_NAME = "io.brooklyn.camp.dto.PlatformDto";
    static { assert CLASS_NAME.equals(PlatformDto.class.getCanonicalName()); }

    protected PlatformDto() {}
    protected PlatformDto(DtoFactory dtoFactory, PlatformRootSummary x) {
        super(dtoFactory, x);
        platformComponentTemplates = new ArrayList<LinkDto>();
        for (Link<PlatformComponentTemplate> t: dtoFactory.getPlatform().platformComponentTemplates().links()) {
            platformComponentTemplates.add(LinkDto.newInstance(dtoFactory, PlatformComponentTemplate.class, t));
        }
        
        applicationComponentTemplates = new ArrayList<LinkDto>();
        for (Link<ApplicationComponentTemplate> t: dtoFactory.getPlatform().applicationComponentTemplates().links()) {
            applicationComponentTemplates.add(LinkDto.newInstance(dtoFactory, ApplicationComponentTemplate.class, t));
        }

        assemblyTemplates = new ArrayList<LinkDto>();
        for (Link<AssemblyTemplate> t: dtoFactory.getPlatform().assemblyTemplates().links()) {
            assemblyTemplates.add(LinkDto.newInstance(dtoFactory, AssemblyTemplate.class, t));
        }

        platformComponents = new ArrayList<LinkDto>();
        for (Link<PlatformComponent> t: dtoFactory.getPlatform().platformComponents().links()) {
            platformComponents.add(LinkDto.newInstance(dtoFactory, PlatformComponent.class, t));
        }
        
        applicationComponents = new ArrayList<LinkDto>();
        for (Link<ApplicationComponent> t: dtoFactory.getPlatform().applicationComponents().links()) {
            applicationComponents.add(LinkDto.newInstance(dtoFactory, ApplicationComponent.class, t));
        }

        assemblies = new ArrayList<LinkDto>();
        for (Link<Assembly> t: dtoFactory.getPlatform().assemblies().links()) {
            assemblies.add(LinkDto.newInstance(dtoFactory, Assembly.class, t));
        }

        // TODO set custom fields

        apidoc = LinkDto.newInstance(
                dtoFactory.getUriFactory().uriOfRestResource(ApidocRestResource.class),
                "API documentation");
    }

    // TODO add custom fields
    private List<LinkDto> assemblyTemplates;
    private List<LinkDto> platformComponentTemplates;
    private List<LinkDto> applicationComponentTemplates;
    private List<LinkDto> assemblies;
    private List<LinkDto> platformComponents;
    private List<LinkDto> applicationComponents;
    
    // non-CAMP, but useful
    private LinkDto apidoc;
    
    public List<LinkDto> getAssemblyTemplates() {
        return assemblyTemplates;
    }
    
    public List<LinkDto> getPlatformComponentTemplates() {
        return platformComponentTemplates;
    }
    
    public List<LinkDto> getApplicationComponentTemplates() {
        return applicationComponentTemplates;
    }
    
    public List<LinkDto> getAssemblies() {
        return assemblies;
    }
    
    public List<LinkDto> getPlatformComponents() {
        return platformComponents;
    }
    
    public List<LinkDto> getApplicationComponents() {
        return applicationComponents;
    }
    
    public LinkDto getApidoc() {
        return apidoc;
    }
    
    // --- building ---

    public static PlatformDto newInstance(DtoFactory dtoFactory, PlatformRootSummary x) {
        return new PlatformDto(dtoFactory, x);
    }

}
