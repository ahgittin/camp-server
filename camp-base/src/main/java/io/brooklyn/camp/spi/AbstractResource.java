package io.brooklyn.camp.spi;

import io.brooklyn.camp.commontypes.RepresentationSkew;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import brooklyn.util.text.Identifiers;
import brooklyn.util.time.Time;

import com.google.common.collect.ImmutableList;

/** Superclass of CAMP resource implementation objects.
 * Typically used to hold common state of implementation objects
 * and to populate the DTO's used by the REST API.
 * <p>
 * These class instances are typically created using the 
 * static {@link #builder()} methods they contain. 
 * The resulting instances are typically immutable,
 * so where fields can change callers should use a new builder
 * (or update an underlying data store).
 * <p>
 * This class is not meant to be instantiated directly, as
 * CAMP only uses defined subclasses (ie containing these fields).
 * It is instantiable for testing.
 */
public class AbstractResource {

    public static final String CAMP_TYPE = "Resource";
    
    private String id = Identifiers.makeRandomId(8);
    private String name;
    private String type;
    private String description;
    private Date created = Time.dropMilliseconds(new Date());
    private List<String> tags = Collections.emptyList();
    private RepresentationSkew representationSkew;
    
    /** Use {@link #builder()} to create */
    protected AbstractResource() {}
    
    // getters

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public String getDescription() {
        return description;
    }
    public Date getCreated() {
        return created;
    }
    public List<String> getTags() {
        return tags;
    }
    public RepresentationSkew getRepresentationSkew() {
        return representationSkew;
    }
    
    // setters

    private void setId(String id) {
        this.id = id;
    }
    private void setName(String name) {
        this.name = name;
    }
    private void setDescription(String description) {
        this.description = description;
    }
    private void setCreated(Date created) {
        // precision beyond seconds breaks equals check
        this.created = Time.dropMilliseconds(created);
    }
    private void setTags(List<String> tags) {
        this.tags = ImmutableList.copyOf(tags);
    }
    private void setType(String type) {
        this.type = type;
    }
    private void setRepresentationSkew(RepresentationSkew representationSkew) {
        this.representationSkew = representationSkew;
    }
            
    // builder
    @SuppressWarnings("rawtypes")
    public static Builder<? extends AbstractResource,? extends Builder> builder() {
        return new AbstractResourceBuilder(CAMP_TYPE);
    }
    
    /** Builder creates the instance up front to avoid repetition of fields in the builder;
     * but prevents object leakage until build and prevents changes after build,
     * so effectively immutable.
     * <p>
     * Similarly setters in the class are private so those objects are also typically effectively immutable. */
    public abstract static class Builder<T extends AbstractResource,U extends Builder<T,U>> {
        
        private boolean built = false;
        private String type = null;
        private T instance = null;
        
        protected Builder(String type) {
            this.type = type;
        }
        
        @SuppressWarnings("unchecked")
        protected T createResource() {
            return (T) new AbstractResource();
        }
        
        protected synchronized T instance() {
            if (built) 
                throw new IllegalStateException("Builder instance from "+this+" cannot be access after build");
            if (instance==null) {
                instance = createResource();
                initialize();
            }
            return instance;
        }

        protected void initialize() {
            if (type!=null) type(type);
        }
        
        public synchronized T build() {
            T result = instance();
            built = true;
            return result;
        }
        
        @SuppressWarnings("unchecked")
        protected U thisBuilder() { return (U)this; }
        
        public U type(String x) { instance().setType(x); return thisBuilder(); }
        public U id(String x) { instance().setId(x); return thisBuilder(); }
        public U name(String x) { instance().setName(x); return thisBuilder(); }
        public U description(String x) { instance().setDescription(x); return thisBuilder(); }
        public U created(Date x) { instance().setCreated(x); return thisBuilder(); }
        public U tags(List<String> x) { instance().setTags(x); return thisBuilder(); }
        public U representationSkew(RepresentationSkew x) { instance().setRepresentationSkew(x); return thisBuilder(); }
    }

    // only for testing
    protected static class AbstractResourceBuilder extends Builder<AbstractResource,AbstractResourceBuilder> {
        protected AbstractResourceBuilder(String type) {
            super(type);
        }
    }
    
}
