import React from "react";
import iconDatLich from "../assets/images/iconDatLich.jpg";
import iconSanBong from "../assets/images/iconSanBong.jpg";
import biaSanBong from "../assets/images/biaSanBong.jpg";
import { useNavigate } from "react-router-dom";

// prime react
import { Button } from "primereact/button";

function HomePage() {
  const navigate = useNavigate();

  return (
    <div className="">
      <div
        className="col-12 text-center mt-3"
        style={{
          backgroundImage: `url(${biaSanBong})`,
          backgroundSize: "cover",
          backgroundPosition: "center",
          minHeight: "60vh",
          padding: "50px 0",
          color: "#fff",
          display: "flex",
          flexDirection: "column",
          justifyContent: "center",
          alignItems: "center",
        }}
      >
        <div
          style={{
            backgroundColor: "rgba(255, 255, 255, 0.3)",
            backdropFilter: "blur(100px)",
            padding: "20px",
            borderRadius: "10px",
            textAlign: "center",
            width: "50%",
          }}
        >
          <h3 className="text-dark">Hệ thống đặt sân bóng online</h3>
          <p className="text-dark">
            Hệ thống chuyên cung cấp sân bóng uy tín bậc nhất Hà Nội
          </p>
          <Button
            className="rounded-3"
            onClick={() => {
              navigate("/pitch");
            }}
          >
            Tìm kiếm sân ngay
          </Button>
        </div>
      </div>
      <div className="row mt-3 justify-content-around mx-0">
        <div
          className="col-lg-5 col-md-12"
          style={{
            padding: "20px",
            textAlign: "center",
          }}
        >
          <img
            src={iconSanBong}
            alt="Icon sân bóng"
            style={{
              width: "200px",
              height: "auto",
              display: "block",
              margin: "0 auto",
            }}
          />
          <h4>Tìm kiếm vị trí sân</h4>
          <p>
            Dữ liệu sân đấu dồi dào, liên tục cập nhật, giúp bạn dễ dàng tìm
            kiếm theo khu vực mong muốn
          </p>
        </div>
        <div
          className="col-lg-5 col-md-12"
          style={{
            padding: "20px",
            textAlign: "center",
          }}
        >
          <img
            src={iconDatLich}
            alt="Icon sân bóng"
            style={{
              width: "200px",
              height: "auto",
              display: "block",
              margin: "0 auto",
            }}
          />
          <h4>Đặt lịch online</h4>
          <p>
            Không cần đến trực tiếp, không cần gọi điện đặt lịch, bạn hoàn toàn
            có thể đặt sân ở bất kì đâu có internet
          </p>
        </div>
      </div>
      <div className="footer">
        <footer
          className="text-center text-lg-start text-white mt-3"
          style={{ backgroundColor: "#45526e" }}
        >
          <div className="container p-4 pb-0">
            <section className="">
              <div className="row">
                <div className="col-md-3 col-lg-3 col-xl-3 mx-auto mt-3">
                  <h6 className="text-uppercase mb-4 font-weight-bold">
                    Hệ thống quản lý sân bóng
                  </h6>
                  <p>
                    Chúng tôi chuyên cung cấp dịch vụ đặt sân bóng cho mọi người
                  </p>
                </div>
                <hr className="w-100 clearfix d-md-none" />
                <div className="col-md-2 col-lg-2 col-xl-2 mx-auto mt-3">
                  <h6 className="text-uppercase mb-4 font-weight-bold">
                    Công nghệ sử dụng
                  </h6>
                  <p>
                    <a className="text-white">Java Spring Boot</a>
                  </p>
                  <p>
                    <a className="text-white">React Typescript</a>
                  </p>
                  <p>
                    <a className="text-white">MySql</a>
                  </p>
                </div>
                <hr className="w-100 clearfix d-md-none" />
                <div className="col-md-4 col-lg-3 col-xl-3 mx-auto mt-3">
                  <h6 className="text-uppercase mb-4 font-weight-bold">
                    Liên lạc
                  </h6>
                  <p>
                    <i className="fas fa-home mr-3" /> Hà Nội
                  </p>
                  <p>
                    <i className="fas fa-envelope mr-3" />{" "}
                    pitchmanagement@gmail.com
                  </p>
                  <p>
                    <i className="fas fa-phone mr-3" /> + 84 999 888 999
                  </p>
                </div>
              </div>
            </section>
            <hr className="my-3" />
            <section className="p-3 pt-0">
              <div className="row d-flex align-items-center">
                <div className="col-md-7 col-lg-8 text-center text-md-start">
                  <div className="p-3">
                    © 2020 Copyright:
                    <a
                      className="text-white"
                      href="https://mdbootstrap.com/"
                      target="_blank"
                      rel="noopener noreferrer"
                    >
                      MDBootstrap.com
                    </a>
                  </div>
                </div>
                <div className="col-md-5 col-lg-4 ml-lg-0 text-center text-md-end">
                  <a
                    className="btn btn-outline-light btn-floating m-1"
                    role="button"
                    href="https://facebook.com"
                    target="_blank"
                    rel="noopener noreferrer"
                  >
                    <i className="pi pi-facebook" />
                  </a>
                  <a
                    className="btn btn-outline-light btn-floating m-1"
                    role="button"
                    href="https://instagram.com"
                    target="_blank"
                    rel="noopener noreferrer"
                  >
                    <i className="pi pi-instagram" />
                  </a>
                </div>
              </div>
            </section>
          </div>
        </footer>
      </div>
    </div>
  );
}

export default HomePage;
