package music.spring.data.neo4j.services;


import org.springframework.data.neo4j.repository.GraphRepository;

public abstract class GenericService <T> implements Service<T> {
	
	private static final int DEPTH_LIST = 0;
    private static final int DEPTH_ENTITY = 1;

    @Override
    public Iterable<T> findAll() {
        return getRepository().findAll(DEPTH_LIST);
    }

    @Override
    public T find(Long id) {
        return getRepository().findOne(id, DEPTH_ENTITY);
    }

    @Override
    public void delete(Long id) {
        getRepository().delete(id);
    }
 
    @Override
    public T createOrUpdate(T entity) {
    	return getRepository().save(entity);
    }

    public abstract GraphRepository<T> getRepository();

}
