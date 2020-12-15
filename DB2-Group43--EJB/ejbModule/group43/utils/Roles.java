package group43.utils;

public enum Roles {
	
	USER("user"), ADMIN("admin"), NONE("none");
	
	private String role;
	
	private Roles(String role) {
		this.role = role;
	}
	
	public String toString() {
		return this.role;
	}
	
	public static Roles getRoleByString(String role) {
		if(role.equals("user")) {
			return USER;
		} else if(role.equals("admin")) {
			return ADMIN;
		} else {
			return NONE;
		}
	}
	
}
