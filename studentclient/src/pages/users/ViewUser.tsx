import React, {useEffect, useState} from "react";
import styled from "styled-components";
import { Link, useParams } from "react-router-dom";
import axios from "axios";

const ViewUser = () => {

  const { id } = useParams<{id: string}>();
  const [user, setUser] = useState({
    name: "",
    address: "",
    email: "",
  });

  const { name, address, email } = user;

  const loadUser = async () => {
    const result = await axios.get(`http://localhost:9090/api/v1/user/get/${id}`);
    console.log(result);
    setUser(result.data);
  };

  useEffect(() => {
    console.log(`Hello World, id is ` + id);
    loadUser();
  }, [id]);

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center mb-4">User Detail</h2>
          <div className="card">
            <div className="card-header">
              Details of user id:
              <ul className="list-group list-group-flush">
                <li className="list-group-item">
                  <b>Name:</b>&nbsp;{user?.name ?? 'N/A'}
                </li>
                <li className="list-group-item">
                  <b>Address:</b>&nbsp;{user?.address ?? 'N/A'}
                </li>
                <li className="list-group-item">
                  <b>Email:</b>&nbsp;{user?.email ?? 'N/A'}
                </li>
              </ul>
            </div>
          </div>
          <DivWrapper to="/" className="btn btn-outline-primary mx-2 View">Home</DivWrapper>
        </div>
      </div>
    </div>
  );
};

export default ViewUser;

const DivWrapper = styled(Link)`
  margin-top: 20px;
  border: none;
  display: flex;
  justify-content: center;
  align-items: center;
`;
