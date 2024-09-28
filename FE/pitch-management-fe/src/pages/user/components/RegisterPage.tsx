import React, { useRef, useState } from "react";
import UserModel from "../../../models/UserModel";
import { Link, Navigate, useNavigate } from "react-router-dom";
import AuthUtil from "../../../utils/AuthUtil";
import UserService from "../../../services/UserService";

// prime react
import { Divider } from "primereact/divider";
import { InputText } from "primereact/inputtext";
import { Button } from "primereact/button";
import { Toast } from "primereact/toast";

function RegisterPage() {
  let _user: UserModel = {
    email: "",
    password: "",
    address: "",
    fullName: "",
    phone: "",
    avatar: "",
  };

  const [user, setUser] = useState<UserModel>(_user);

  const toast = useRef<Toast>(null);

  // Chuyển trang
  const navigate = useNavigate();

  const handleSubmit = async (e: any) => {
    e.preventDefault();

    try {
      const userData = await UserService.register(user);
      const userResult = userData.result;

      navigate("/login");
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

  const handleInputChange = (e: any) => {
    const { name, value } = e.target;
    setUser({ ...user, [name]: value });
  };

  const base64ConversionForImages = async (e: any) => {
    if (e.target.files[0]) {
      getBase64(e.target.files[0]);
    }
  };

  const getBase64 = (file: File) => {
    let reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function () {
      if (typeof reader.result === "string") {
        setUser({ ...user, avatar: reader.result });
        console.log("image ", reader.result);
      }
    };
    reader.onerror = function (error) {
      console.log("Error", error);
    };
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
            <input type="file" onChange={base64ConversionForImages} />
            <div className="d-flex align-items-center">
              <label className="w-25" htmlFor="">
                Email
              </label>
              <InputText
                className="flex-grow-1"
                name="email"
                type="text"
                value={user.email}
                onChange={handleInputChange}
                required
              ></InputText>
            </div>
            <div className="d-flex align-items-center">
              <label className="w-25" htmlFor="">
                Mật khẩu
              </label>
              <InputText
                className="flex-grow-1"
                name="password"
                type="password"
                value={user.password}
                onChange={handleInputChange}
                required
              ></InputText>
            </div>
            <div className="d-flex align-items-center">
              <label className="w-25" htmlFor="">
                Họ và tên
              </label>
              <InputText
                className="flex-grow-1"
                name="fullName"
                type="text"
                value={user.fullName}
                onChange={handleInputChange}
                required
              ></InputText>
            </div>
            <div className="d-flex align-items-center">
              <label className="w-25" htmlFor="">
                Địa chỉ
              </label>
              <InputText
                className="flex-grow-1"
                name="address"
                type="text"
                value={user.address}
                onChange={handleInputChange}
                required
              ></InputText>
            </div>
            <div className="d-flex align-items-center">
              <label className="w-25" htmlFor="">
                Số điện thoại
              </label>
              <InputText
                className="flex-grow-1"
                name="phone"
                type="text"
                value={user.phone}
                onChange={handleInputChange}
                required
              ></InputText>
            </div>
            <Button
              className="rounded-3"
              label="Đăng ký"
              icon="pi pi-user"
            ></Button>
          </form>
          <Divider>OR</Divider>
          <Link to={"/login"}>
            <Button
              className="rounded-3 w-100"
              label="Đăng nhập"
              icon="pi pi-user-plus"
              severity="success"
            ></Button>
          </Link>
        </div>
      </div>
    </>
  );
}

export default RegisterPage;
