package dojoservlets.data;

import java.util.List;

import dojoservlets.domain.Client;

public interface ClientDao {

	public List<Client> findAll();
	
	public Client find(Client client);
	
	public int insert(Client client);
	
	public int update(Client client);
	
	public int delete(Client client);
}
