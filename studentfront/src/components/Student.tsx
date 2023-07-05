import React, { useState, useEffect } from "react";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Container from "@mui/material/Container";
import Paper from "@mui/material/Paper";
import styled from "styled-components";
import SendButton from "./SendButton";
import { StudentData } from "../datatypes/constref";

const Student: React.FC = () => {
  const paperStyle = {
    padding: "50px 20px",
    width: 600,
    margin: "20px auto",
  };

  const [name, setName] = useState("");
  const [address, setAddress] = useState("");
  const [students, setStudents] = useState<StudentData[]>([]);

  /**
   *
   * @param e
   */
  const addStudent = async (e: any) => {
    e.preventDefault();
    try {
      const student: StudentData = { name, address };
      console.log(student);

      const response = await fetch("http://localhost:9090/api/v1/student/add", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(student),
      });
      console.log(response);
    } catch (error: any) {
      console.error(error?.message);
    }
  };

  useEffect(() => {
    const getStudents = async () => {
      try {
        const response = await fetch("http://localhost:9090/api/v1/student/all");
        const jsonData = await response.json();
        setStudents(jsonData);
      } catch (error: any) {
        console.error(error?.message);
      }
    };
    getStudents();
  }, []);

  return (
    <Container>
      <Paper elevation={3} style={paperStyle}>
        <h1 style={{ color: "bule" }}>Add Customer</h1>
        <Box
          component="form"
          sx={{
            "& > :not(style)": { m: 1, width: "100%" },
          }}
          noValidate
          autoComplete="off"

        >
          <StyledDiv>
            <TextField
              id="outlined-basic"
              label="Name"
              variant="outlined"
              fullWidth
              value={name}
              onChange={(e) => setName(e.target.value)}
            />
            <TextField
              id="outlined-basic"
              label="Address"
              variant="outlined"
              fullWidth
              value={address}
              onChange={(e) => setAddress(e.target.value)}
            />
            <SendButton addStudent ={addStudent}/>
          </StyledDiv>
        </Box>
      </Paper>

      <Paper elevation={3} style={{...paperStyle, textAlign: "left"}}>
        <h1 style={{ color: "bule", textAlign: "center" }}>Customer List</h1>
        {students.length && students.map((student: StudentData) => (
            <Paper key={student.id} style={{marginTop: "1rem"}}>
              {student.id}<br/>
              {student.name}<br/>
              {student.address}<br/>
            </Paper>
          ))}
      </Paper>
    </Container>
  );
};

export default Student;

const StyledDiv = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  gap: 1rem;
`;
