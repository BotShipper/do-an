import React, { useRef, useState } from "react";
import { Link, Navigate, useNavigate } from "react-router-dom";
import UserService from "../../../services/UserService";
import AuthUtil from "../../../utils/AuthUtil";

// prime react
import { Divider } from "primereact/divider";
import { InputText } from "primereact/inputtext";
import { Button } from "primereact/button";
import { Toast } from "primereact/toast";

function LoginPage() {
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");

  const toast = useRef<Toast>(null);

  // Chuyển trang
  const navigate = useNavigate();

  const handleSubmit = async (e: any) => {
    e.preventDefault();

    try {
      const userData = await UserService.login(email, password);
      const userResult = userData.result;

      // Đưa dữ liệu vào storage
      localStorage.setItem("token", userResult.token);
      localStorage.setItem("role", userResult.role);
      localStorage.setItem("fullName", userResult.fullName);

      navigate("/register");
    } catch (error: any) {
      console.error(error.message);
      toast.current?.show({
        severity: "error",
        summary: "Error",
        detail: `${error.message}`,
        life: 3000,
      });
    }
  };

  if (AuthUtil.isAuthenticated()) {
    return <Navigate to={"/home"} />;
  }

  return (
    <>
      <Toast ref={toast} />
      <div className="container-fluid d-flex justify-content-center align-items-center h-100">
        <div className="card w-50 p-5">
          <form className="d-flex flex-column gap-3" onSubmit={handleSubmit}>
            <div className="d-flex align-items-center">
              <label className="w-25" htmlFor="">
                Email
              </label>
              <InputText
                className="flex-grow-1"
                name="username"
                type="text"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
              ></InputText>
            </div>
            <div className="d-flex align-items-center">
              <label className="w-25" htmlFor="">
                Password
              </label>
              <InputText
                className="flex-grow-1"
                name="password"
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
              ></InputText>
            </div>
            <Button
              className="rounded-3"
              label="Đăng nhập"
              icon="pi pi-user"
            ></Button>
          </form>
          <Divider>OR</Divider>
          <Link to={"/register"}>
            <Button
              className="rounded-3 w-100"
              label="Đăng ký"
              icon="pi pi-user-plus"
              severity="success"
            ></Button>
          </Link>
        </div>
      </div>
    </>
  );
}

export default LoginPage;
