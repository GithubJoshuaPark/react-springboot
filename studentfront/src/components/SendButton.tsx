import React from 'react';
import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';
import styled from "styled-components";
import { addStudentProp } from '../datatypes/constref';

const SendButton: React.FC<addStudentProp>= ({addStudent}) => {
  return (
    <StyledDiv>
      <Stack spacing={2} direction="row">
        <Button variant="outlined" onClick={addStudent}>Add</Button>
      </Stack>
    </StyledDiv>
  );
}

export default SendButton;

const StyledDiv = styled.div`
  margin: 0.5rem auto;
`