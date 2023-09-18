import "./App.css";
import { useEffect, useState } from "react";
import axios from "axios";
import {
  Button,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TablePagination,
  TableRow,
} from "@mui/material";

import AddIcon from "@mui/icons-material/Add";
import UserModal from "./modal";

const columns = [
  { id: "id", label: "ID", minWidth: 170 },
  { id: "email", label: "Email", minWidth: 170 },
  { id: "first_name", label: "First Name", minWidth: 170 },
  { id: "last_name", label: "Last Name", minWidth: 170 },
  { id: "age", label: "Age", minWidth: 170 },
];

const defaultdata = {
  id: "",
  first_name: "",
  last_name: "",
  email: "",
  age: "",
};

function App() {
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(10);
  const [openusermodal, setopenusermodal] = useState(false);
  const [selecteduser, setSelecteduser] = useState(defaultdata);
  const [submittype,setSubmittype] = useState("")
  const [refreshdata,setrefreshdata] = useState(true)
  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(+event.target.value);
    setPage(0);
  };

  const [userTable, setUserTable] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8031/api/users/allusers").then((res) => {
      console.log(res.data);
      setUserTable(res.data);
    });
  }, [refreshdata]);

  return (
    <div className="App">
      <Paper sx={{ width: "100%", overflow: "hidden" }}>
        <TableContainer sx={{ maxHeight: 440 }}>
          <Table stickyHeader aria-label="sticky table">
            <TableHead>
              <TableRow>
                <TableCell align="center" colSpan={10}>
                  <Button onClick={(e) => {setopenusermodal(true);setSubmittype('create')}}>
                    <AddIcon />
                  </Button>
                </TableCell>
              </TableRow>
              <TableRow>
                {columns.map((column) => (
                  <TableCell
                    key={column.id}
                    align={column.align}
                    style={{ minWidth: column.minWidth }}
                  >
                    {column.label}
                  </TableCell>
                ))}
              </TableRow>
            </TableHead>
            <TableBody>
              {userTable
                .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                .map((row) => {
                  return (
                    <TableRow
                      hover
                      role="checkbox"
                      tabIndex={-1}
                      key={row.code}
                      onClick={(e) => {
                        setSelecteduser(row);
                        setSubmittype('update');
                        setopenusermodal(true);
                      }}
                    >
                      {columns.map((column) => {
                        const value = row[column.id];
                        return (
                          <TableCell key={column.id} align={column.align}>
                            {column.format && typeof value === "number"
                              ? column.format(value)
                              : value}
                          </TableCell>
                        );
                      })}
                    </TableRow>
                  );
                })}
            </TableBody>
          </Table>
        </TableContainer>
        <TablePagination
          rowsPerPageOptions={[10, 25, 100]}
          component="div"
          count={userTable.length}
          rowsPerPage={rowsPerPage}
          page={page}
          onPageChange={handleChangePage}
          onRowsPerPageChange={handleChangeRowsPerPage}
        />
      </Paper>
      <UserModal
        open={openusermodal}
        setOpen={setopenusermodal}
        data={selecteduser}
        setdata={setSelecteduser}
        submittype ={submittype}
        setrefreshdata={setrefreshdata}
        refreshdata={refreshdata}
      />
    </div>
  );
}

export default App;
