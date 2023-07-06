import React, { useState, ChangeEvent, FormEvent } from "react";
import { Link, useHref } from "react-router-dom";
import styled from "styled-components";
import { User } from "../../datatypes/constref";
import axios from "axios";

const AddUser: React.FC = () => {
  const [user, setUser] = useState({
    name: "",
    address: "",
    email: "",
  });

  const { name, address, email } = user;
  const onInputChange = (e: ChangeEvent<HTMLInputElement>) => {
    console.log(`🍎 ~ file: AddUser.tsx:18 ~ onInputChange ~ e:`, e);
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const onSubmit = async(e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    console.log(`🍎 ~ file: AddUser.tsx:24 ~ onSubmit ~ user:`, user);
    await axios.post("http://localhost:9090/api/v1/user/add", user);
    setUser({ name: "", address: "", email: "" });
    window.location.href = "/";
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center mb-4">Add User</h2>
          <form onSubmit={(e) => onSubmit(e)}>
            {/* name */}
            <div className="mb-3">
              <label htmlFor="name" className="form-label">
                Name
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter name"
                name="name"
                value={name}
                onChange={e => onInputChange(e)}
              ></input>
            </div>
            {/* address */}
            <div className="mb-3">
              <label htmlFor="address" className="form-label">
                Address
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter address"
                name="address"
                value={address}
                onChange={e => onInputChange(e)}
              ></input>
            </div>
            {/* email */}
            <div className="mb-3">
              <label htmlFor="email" className="form-label">
                Email
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter email"
                name="email"
                value={email}
                onChange={e => onInputChange(e)}
              ></input>
            </div>
            {/* buttons */}
            <DivWrapper className="mb-3">
              <button type="submit" className="btn btn-outline-primary">
                Submit
              </button>
              <Link to="/" type="button" className="btn btn-outline-danger mx-2">
                Cancel
              </Link>
            </DivWrapper>
          </form>
        </div>
      </div>
    </div>
  );
};

export default AddUser;

const DivWrapper = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
`;
