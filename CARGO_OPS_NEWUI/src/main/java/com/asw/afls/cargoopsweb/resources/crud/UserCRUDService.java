package com.asw.afls.cargoopsweb.resources.crud;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.asw.afls.cargoopsweb.annotations.CacheAnnotations.NoCache;
import com.asw.afls.cargoopsweb.model.crud.User;
import com.asw.afls.cargoopsweb.utility.crud.UserCRUDUtility;
import com.asw.afls.cargoopsweb.validator.crud.UserCRUDValidator;

@Component
@Path("/users")
public class UserCRUDService {

	@Autowired
	@Qualifier("userCRUDUtility")
	private UserCRUDUtility utility;

	@Autowired
	@Qualifier("userCRUDValidator")
	private UserCRUDValidator validator;

	/*
	 * @CacheMaxAge(time = 10, unit = TimeUnit.MINUTES)
	 */

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@NoCache
	public List<User> getAllUsers() {
		return utility.getAllUsers();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@NoCache
	public User getUserById(@PathParam("id") int id) {
		return utility.getById(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User create(@NotNull(message = "{user.required}") @Valid User user) {
		return utility.createNewUser(user);
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User update(@Valid User user) {
		return utility.update(user);
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void remove(@PathParam("id") int id) {
		utility.remove(id);
	}

	@GET
	@Path("numberOfUsers")
	@Produces(MediaType.TEXT_PLAIN)
	public int getNumberOfUsers() {
		return utility.getNumberOfUsers();
	}

}
