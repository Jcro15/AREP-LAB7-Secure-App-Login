package edu.escuelaing.arep.model;

/**
 * Representación de un usario del sistema
 */
public class User {
    private String email;
    private String password;

    public User() {
    }

    /**
     * retorna el email del usuario
     * @return retorna el email del usuario
     */
    public String getEmail() {
        return email;
    }

    /**
     * establece el correo del usuario
     * @param email el correo a establecer
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * retorna el Hash de la contraseña del usuario
     * @return el Hash de la contraseña del usuario
     */
    public String getPassword() {
        return password;
    }

    /**
     * establece el Hash de la contraseña del usuario
     * @param password el hash de la contraseña del usuario
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
