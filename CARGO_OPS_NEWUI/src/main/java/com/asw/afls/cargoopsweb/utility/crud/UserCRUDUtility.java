package com.asw.afls.cargoopsweb.utility.crud;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import com.asw.afls.cargoopsweb.model.crud.User;
import com.asw.afls.cargoopsweb.translator.crud.UserCRUDTranslator;

public class UserCRUDUtility {
	private static final Map<Integer, User> users = initUsers();
	private UserCRUDTranslator translator = null;

	public UserCRUDTranslator getTranslator() {
		return translator;
	}

	public void setTranslator(UserCRUDTranslator translator) {
		this.translator = translator;
	}

	private static Map<Integer, User> initUsers() {
		Calendar today = null;
		Calendar startDt = null;
		Calendar endDt = null;
		Random generator = new Random();
		Map<Integer, User> users = new HashMap<Integer, User>();
		User user = null;
		for (int i = 0; i < 10; i++) {
			today = Calendar.getInstance();
			today.add(Calendar.YEAR, generator.nextInt(70) * -1);


			user = new User();
			user.setId(i + 1);
			user.setFirstName("Foo" + (i + 1));
			user.setLastName("Bar" + (i + 1));
			user.setDob(today.getTime());
			users.put(user.getId(), user);

			startDt = Calendar.getInstance();
			startDt.add(Calendar.DATE, 1);

			endDt = Calendar.getInstance();
			endDt.add(Calendar.DATE, generator.nextInt(1000) * 1);

			user.setStartDt(startDt.getTime());
			user.setEndDt(endDt.getTime());
			
			user.setEmail(user.getFirstName().toLowerCase() + "@accenture.com");

		}
		return users;
	}

	private static final Comparator<User> USER_COMPARATOR = new Comparator<User>() {
		@Override
		public int compare(User u1, User u2) {
			return (u1.getId() < u2.getId() ? -1 : (u1.getId() == u2.getId() ? 0 : 1));
		}
	};

	private static int getCurrentMaxId() {
		List<User> result = getAllUsers();
		if (result.size() > 0) {
			return result.get(result.size()-1).getId();
		}
		return 0;
	}

	// Service methods
	public static List<User> getAllUsers() {
		List<User> result = new ArrayList<User>(users.values());
		Collections.sort(result, USER_COMPARATOR);
		return result;
	}

	public static User getById(int id) {
		return (users.containsKey(id)) ? users.get(id) : null;
	}

	public static User createNewUser(User user) {
		user.setId(getCurrentMaxId() + 1);
		users.put(user.getId(), user);
		return user;
	}

	public static User update(User user) {
		if (users.containsKey(user.getId())) {
			users.put(user.getId(), user);
		}
		return user;
	}

	public static void remove(int id) {
		if (users.containsKey(id)) {
			users.remove(id);
		}
	}
	public static int getNumberOfUsers() {
		return users.size();
	}
}
