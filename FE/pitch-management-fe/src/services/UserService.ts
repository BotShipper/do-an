import axios from "axios";
import UserModel from "../models/UserModel";

class UserService {

    static BASE_URL = "http://localhost:8080/pitch-management/api";

    static async login(email: string, password: string) {
        try {
            const response = await axios.post(`${this.BASE_URL}/public/login`, {email, password});
            return response.data;
            
        } catch (error) {
            throw error;
        }
    }

    static async register(request : UserModel) {
        try {
            const response = await axios.post(`${this.BASE_URL}/public/register`, request);
            return response.data;
            
        } catch (error) {
            throw error;
        }
    }
}

export default UserService;