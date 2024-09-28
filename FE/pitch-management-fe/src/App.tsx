import "./App.css";
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import Navbar from "./common/Navbar";
import LoginPage from "./pages/user/components/LoginPage";
import UserPage from "./pages/user/UserPage";
import HomePage from "./pages/HomePage";
import PitchPage from "./pages/pitch/PitchPage";
import HistoryPage from "./pages/history/HistoryPage";
import AdminPage from "./pages/admin/AdminPage";
import RegisterPage from "./pages/user/components/RegisterPage";

function App() {
  return (
    <div className="d-flex flex-column vh-100">
      <div className="flex-shrink-0">
        <Navbar />
      </div>
      <div className="flex-grow-1">
        <Routes>
          {/* <Route path="/" element={<HomePage />} /> */}
          <Route path="/home" element={<HomePage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/user" element={<UserPage />} />
          <Route path="/pitch" element={<PitchPage />} />
          <Route path="/history" element={<HistoryPage />} />
          <Route path="/admin" element={<AdminPage />} />
          <Route path="*" element={<Navigate to={"/home"} />} />
        </Routes>
      </div>
    </div>
  );
}

export default App;
