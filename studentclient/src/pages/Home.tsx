import React, { useState, useEffect } from "react";
import axios from "axios";
import { User } from "../datatypes/constref";

const Home: React.FC = () => {
  const [users, setUsers] = useState<User[]>([]);

  const loadUsers = async () => {
    const result = await axios.get("http://localhost:9090/api/v1/user/all");
    console.log(result);
    setUsers(result.data.reverse());
  };

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
                  <button className="btn btn-primary mx-2">View</button>
                  <button className="btn btn-outline-primary mx-2">Edit</button>
                  <button className="btn btn-danger mx-2">Delete</button>
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
