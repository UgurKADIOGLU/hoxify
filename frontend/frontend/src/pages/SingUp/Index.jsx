import React, { useEffect, useMemo, useState } from "react";
import { singUp } from "./api";
import { Input } from "./commponents/input";

function SingUp() {
  const [username, setUserName] = useState();
  const [email, setEmail] = useState();
  const [password, setPassword] = useState();
  const [rePassword, setRePassword] = useState();
  const [apiProgres, setApiProgres] = useState(false);
  const [successMesage, setSuccessMesage] = useState();
  const [errors, setErrors] = useState({});
  const [generalError, setGeneralError] = useState();

  useEffect(() => {
    setErrors(function (lastErrors) {
      return {
        ...lastErrors,
        username: undefined,
      };
    });
  }, [username]);

  useEffect(() => {
    setErrors(function (lastErrors) {
      return {
        ...lastErrors,
        email: undefined,
      };
    });
  }, [email]);

  useEffect(() => {
    setErrors(function (lastErrors) {
      return {
        ...lastErrors,
        password: undefined,
      };
    });
  }, [password]);

  useEffect(() => {
    setErrors(function (lastErrors) {
      return {
        ...lastErrors,
        rePassword: undefined,
      };
    });
  }, [rePassword]);

  const onSubmit = async (event) => {
    event.preventDefault();
    setApiProgres(true);
    setSuccessMesage();
    setGeneralError();

    try {
      const response = await singUp({
        username,
        email,
        password,
      });
      setSuccessMesage(response.data.message);
      setErrors({});
    } catch (axiosError) {
      if (
        axiosError.response?.data &&
        axiosError.response.data.status === 400
      ) {
        setErrors(axiosError.response.data.validationErrors);
      } else {
        setGeneralError("Unexpected error occured. Please try again.");
      }
    } finally {
      setApiProgres(false);
    }
  };

  let passwordRepeatError = useMemo(() => {
    if (password && password !== rePassword) {
     return "password miss mach";
    }
  }, [password, rePassword]);

  return (
    <div className="container text-center">
      <div className="col-lg-6 offset-lg-3">
        <form className="card" onSubmit={onSubmit}>
          <div className="card-header">
            <h1>Sing Up</h1>
          </div>
          <div className="card-body">
            <Input
              id="username"
              label="User Name"
              error={errors.username}
              onChange={(event) => {
                setUserName(event.target.value);
              }}
            />

            {/* <div className="mb-3">
              <label htmlFor="username" className="form-label">
                User Name
              </label>
              <input
                id="username"
                className={errors.username ? "form-control is-invalid" : "form-control"}
                onChange={(event) => {
                  setUserName(event.target.value);
                }}
              ></input>
              <div className="invalid-feedback">
                {errors.username}
              </div>
            </div> */}

            <Input
              id="email"
              label="E-mail"
              error={errors.email}
              onChange={(event) => {
                setEmail(event.target.value);
              }}
            />

            {/* <div className="mb-3">
              <label htmlFor="email" className="form-label">
                E-mail
              </label>
              <input
                id="email"
                className={errors.email ? "form-control is-invalid" : "form-control"}
                
                onChange={(event) => {
                  setEmail(event.target.value);
                }}
              ></input>
              <div className="invalid-feedback">
                {errors.email}
              </div>
            </div> */}
            <Input
              id="password"
              label="Password"
              error={errors.password}
              onChange={(event) => {
                setPassword(event.target.value);
              }}
              type="password"
            />
            {/* <div className="mb-3">
              <label htmlFor="password" className="form-label">
                Password
              </label>
              <input
                id="password"
                className="form-control"
                type="password"
                onChange={(event) => {
                  setPassword(event.target.value);
                }}
              ></input>
            </div> */}
            <Input
              id="rePassword"
              label="Password Repeat"
              error={errors.rePassword}
              onChange={(event) => {
                setRePassword(event.target.value);
              }}
              type="password"
            />
            {/* <div className="mb-3">
              <label htmlFor="rePassword" className="form-label">
                Password Repeat
              </label>
              <input
                id="rePassword"
                className="form-control"
                type="password"
                onChange={(event) => {
                  setRePassword(event.target.value);
                }}
              ></input>
            </div> */}

            {successMesage && <div>{successMesage}</div>}
            {generalError && <div>{generalError}</div>}
            {passwordRepeatError && <div>{passwordRepeatError}</div>}
            <button
              disabled={apiProgres || (password === rePassword ? false : true)}
              className="btn btn-primary"
            >
              {apiProgres && (
                <span
                  class="spinner-border spinner-border-sm"
                  aria-hidden="true"
                ></span>
              )}
              Sing Up
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default SingUp;
