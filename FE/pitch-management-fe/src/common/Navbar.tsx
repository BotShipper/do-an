import React, { useRef, useState } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";
import AuthUtil from "../utils/AuthUtil";
import soccerBall from "../assets/images/soccerBall.png";
import defaultAvatar from "../assets/images/defaultAvatar.jpg";

// prime react
import { Button } from "primereact/button";
import { Avatar } from "primereact/avatar";
import { Menu } from "primereact/menu";
import { MenuItem } from "primereact/menuitem";
import { Toast } from "primereact/toast";
import { Dialog } from "primereact/dialog";

function Navbar() {
  const [visible, setVisible] = useState<boolean>(false);
  const avatar = useRef<Menu>(null);
  const toast = useRef<Toast>(null);
  const navigate = useNavigate();
  const location = useLocation();
  const isAuthenticated = AuthUtil.isAuthenticated();
  const isAdmin = AuthUtil.isAdmin();

  // Xử lý đăng xuất
  const handleLogout = () => {
    setVisible(false);
    AuthUtil.logout();
    toast.current?.show({
      severity: "success",
      summary: "Success",
      detail: "Đăng xuất thành công",
      life: 3000,
    });
  };

  // css
  const navButton = {
    height: "40px",
    padding: "0.7rem",
  };

  const items: MenuItem[] = [
    {
      label: "Thông tin cá nhân",
      icon: "pi pi-refresh",
      command: () => navigate("/profile"),
    },
    {
      label: "Đăng xuất",
      icon: "pi pi-sign-out",
      command: () => setVisible(true),
    },
  ];

  const footerContent = (
    <div>
      <Button
        label="No"
        icon="pi pi-times"
        onClick={() => setVisible(false)}
        className="p-button-text"
      />
      <Button label="Yes" icon="pi pi-check" onClick={handleLogout} autoFocus />
    </div>
  );

  // lấy đường link
  const isActive = (path: string) => {
    return location.pathname.startsWith(path)
      ? "button-active"
      : "button-no-active";
  };

  return (
    <>
      <Toast ref={toast} />
      <nav
        className="d-flex justify-content-between p-3"
        style={{
          backgroundColor: "var(--highlight-bg)",
          borderBottom: "1px solid var(--primary-color)",
          height: "4rem",
        }}
      >
        <div className="d-flex justify-content-start gap-2 align-items-center">
          {/* logo */}
          <img alt="logo" src={soccerBall} height="40" className="me-2"></img>

          {/* Các đường dẫn */}
          <Link to={"/home"}>
            <Button
              className={isActive("/home")}
              label="Trang chủ"
              icon="pi pi-home"
              style={navButton}
            ></Button>
          </Link>
          <Link to={"/pitch"}>
            <Button
              className={isActive("/pitch")}
              label="Sân bóng"
              icon="pi pi-th-large"
              style={navButton}
            ></Button>
          </Link>
          <Link to={"/history"}>
            <Button
              className={isActive("/history")}
              label="Lịch sử đặt"
              icon="pi pi-history"
              style={navButton}
            ></Button>
          </Link>
          <Link to={"/admin"}>
            <Button
              className={isActive("/admin")}
              label="Admin"
              icon="pi pi-lock"
              style={navButton}
            ></Button>
          </Link>
          {/* ------------------------ */}
        </div>
        <div className="d-flex align-items-center gap-2">
          {/* Nếu đã đăng nhập thì hiện thông tin 
        và ngược lại thì hiện nút đăng nhập */}
          {isAuthenticated ? (
            <>
              <span className="">{localStorage.getItem("fullName")}</span>
              <Button className="p-0 rounded-5 border-0">
                <Avatar
                  image={defaultAvatar}
                  shape="circle"
                  size="large"
                  onClick={(event) => avatar.current!.toggle(event)}
                  aria-controls="popup_menu_right"
                  aria-haspopup
                />
              </Button>
              <Menu
                model={items}
                popup
                ref={avatar}
                id="popup_menu_right"
                popupAlignment="right"
              />
            </>
          ) : (
            <Link to={"/login"}>
              <Button
                className="rounded-3"
                label="Đăng nhập"
                icon="pi pi-user"
                severity="success"
              ></Button>
            </Link>
          )}
        </div>
      </nav>
      <Dialog
        header="Header"
        visible={visible}
        style={{ width: "50vw" }}
        onHide={() => {
          if (!visible) return;
          setVisible(false);
        }}
        footer={footerContent}
      >
        <p className="m-0">Bạn có chắc muốn đăng xuất</p>
      </Dialog>
    </>
  );
}

export default Navbar;
