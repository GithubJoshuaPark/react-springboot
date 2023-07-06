import React, { useState, useEffect } from "react";
import axios from "axios";
import { User } from "../datatypes/constref";
import { Link } from "react-router-dom";
import { confirmAlert } from 'react-confirm-alert'; // Import
import 'react-confirm-alert/src/react-confirm-alert.css';

const Home: React.FC = () => {
  const [users, setUsers] = useState<User[]>([]);

  const loadUsers = async () => {
    const result = await axios.get("http://localhost:9090/api/v1/user/all");
    console.log(result);
    setUsers(result.data.reverse());
  };

  const deleteUser = async (id: number) => {
    confirmAlert({
      title: 'Confirm to delete',
      message: 'Are you sure to do this.',
      buttons: [
        {
          label: 'Yes',
          onClick: async () => {
            await axios.delete(`http://localhost:9090/api/v1/user/delete/${id}`);
            loadUsers();
          }
        },
        {
          label: 'No',
        }
      ]
    });
  }

  useEffect(() => {
    console.log("Hello World");
    loadUsers();
  }, []);

  return (
    <div className="container">
      <div className="py-4">
        <table className="table align-middle border shadow table-striped">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">name</th>
              <th scope="col">address</th>
              <th scope="col">email</th>
              <th scope="col">action</th>
            </tr>
          </thead>
          <tbody className="table-group-divider">
            {users.map((user, index) => (
              <tr key={user.id}>
                <th scope="row">{index + 1}</th>
                <td>{user?.name}</td>
                <td>{user?.address}</td>
                <td>{user?.email}</td>
                <td>
                  <Link to={`/viewuser/${user.id}`} className="btn btn-primary mx-2">View</Link>
                  <Link to={`/edituser/${user.id}`} className="btn btn-outline-primary mx-2">Edit</Link>
                  <button className="btn btn-danger mx-2" onClick={() => deleteUser(user.id)}>Delete</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Home;
