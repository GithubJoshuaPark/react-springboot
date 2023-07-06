import React from "react";
import "./App.css";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import AddUser from "./pages/users/AddUser";
import Navbar from "./layout/Navbar";

function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/adduser" element={<AddUser />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
