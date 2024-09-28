class AuthUtil {
    
    static logout() {
        localStorage.removeItem('token');
        localStorage.removeItem('role');
        localStorage.removeItem('fullName');
    }

    static isAuthenticated() {
        const token = localStorage.getItem('token');
        return !!token;
    }

    static isAdmin() {
        const role = localStorage.getItem('role');
        return role === "ADMIN";
    }

    static isUser() {
        const role = localStorage.getItem('role');
        return role === "USER";
    }

    static isAdminOnly() {
        return this.isAuthenticated() && this.isAdmin();
    }
    
}

export default AuthUtil;