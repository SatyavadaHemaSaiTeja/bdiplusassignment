import * as React from "react";
import Box from "@mui/material/Box";
import Modal from "@mui/material/Modal";
import { Button, TextField } from "@mui/material";
import axios from "axios";

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  bgcolor: "background.paper",
  boxShadow: 24,
  p: 4,
};

const fields = [
  {
    label: "ID",
    datakey: "id",
  },
  {
    label: "First Name",
    datakey: "first_name",
  },
  {
    label: "Last Name",
    datakey: "last_name",
  },
  {
    label: "Email",
    datakey: "email",
  },
  {
    label: "Age",
    datakey: "age",
  },
];

const defaultdata = {
  id: "",
  first_name: "",
  last_name: "",
  email: "",
  age: "",
};

export default function UserModal({
  open,
  setOpen,
  data,
  setdata,
  submittype,
  setrefreshdata,
  refreshdata,
}) {
  const handleClose = () => {
    setOpen(false);
    setdata(defaultdata);
  };

  const submit = () => {
    if (submittype === "create") {
      const body = data;
      delete body.id;
      console.log(body);
      axios.post("http://localhost:8031/api/users/add", body).then((res) => {
        console.log(res);
        handleClose();
        setrefreshdata(!refreshdata);
      });
    } else {
      axios
        .put(`http://localhost:8031/api/users/${data.email}`, data)
        .then((res) => {
          console.log(res);
          handleClose();
          setrefreshdata(!refreshdata);
        });
    }
  };

  const changedata = (e, datakey) => {
    setdata((prevState) => ({
      ...prevState,
      [datakey]: e.target.value,
    }));
  };

  const deleteuser = () => {
    axios
      .delete(`http://localhost:8031/api/users/${data.email}`)
      .then((res) => {
        handleClose();
        setrefreshdata(!refreshdata);
      });
  };

  return (
    <div>
      <Modal open={open} onClose={handleClose}>
        <Box sx={style}>
          <form onSubmit={(e) => submit()}>
            {fields.map((fielddata) => (
              <TextField
              type={fielddata.datakey === "email" ? "email" : "text"}
                key={fielddata.label}
                label={fielddata.label}
                id="outlined-size-small"
                value={data[fielddata.datakey]}
                size="small"
                sx={{ m: 1, width: "25ch" }}
                fullWidth
                disabled={fielddata.datakey === "id"}
                required={fielddata.datakey !== "id"}
                onChange={(e) => changedata(e, fielddata.datakey)}
              />
            ))}
            <Button
              sx={{ marginLeft: "200px", marginTop: "10px" }}
              variant={"contained"}
              type="submit"
            >
              {" "}
              Submit{" "}
            </Button>
            {submittype === "update" && (
              <Button
                sx={{ marginLeft: "10px", marginTop: "10px" }}
                variant={"contained"}
                onClick={(e) => deleteuser()}
              >
                {" "}
                Delete{" "}
              </Button>
            )}
          </form>
        </Box>
      </Modal>
    </div>
  );
}
