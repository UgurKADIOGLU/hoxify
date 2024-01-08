import { Component } from "react";
import { useParams } from "react-router-dom";
import { getUser } from "./api";
import { useEffect,useState } from "react";
import { Alert } from "@/shred/componants/Alert";
import { useRouteParamApiRequest } from "@/shred/componants/hooks/useRouteParamApiRequest";

export function User () {

const {apiProgress,data:user,error}=useRouteParamApiRequest('id',getUser)

  // const { id } = useParams();
  // const [apiProgress, setApiProgress] = useState();
  // const [user, setUser] = useState();
  // const [errorMessage, setErrorMessage] = useState();

  // useEffect(() => {
  //   async function loadUser() {
  //     setApiProgress(true);
  //     try {
  //       const response = await getUser(id);
  //       setUser(response.data);
  //     } catch (axiosError) {
  //       setErrorMessage(axiosError.response.data.message);
  //     } finally {
  //       setApiProgress(false);
  //     }
  //   }
  //   loadUser();
  // }, [id]);

  return (
    <>
      {apiProgress && (
        <span className="spinner-border" aria-hidden="true"></span>
      )}
      {user && (
        <h1>{user.username}</h1>
      )}
      {error && <Alert styleType="danger">{error}</Alert>}
    </>
  );
}
//   state = {
//     user: null,
//     apiProgress: false,
//     error: null,
//   };

//   loadUser = async () => {
//     this.setState({ apiProgress: true });
//     try {
//       const response = await getUser(this.props.id);
//       this.setState({
//         user: response.data,
//       });
//     } catch (axiosError) {
//       this.setState({
//         error: axiosError.response.data.message,
//       });
//     } finally {
//       this.setState({ apiProgress: false });
//     }
//   };

//   async componentDidMount() {
//     this.loadUser();
//   }

//   componentDidUpdate(previousProps, previousState) {
//     if (this.props.id !== previousProps.id) {
//       this.loadUser();
//     }
//   }

//   componentWillUnmount() {}

//   render() {
//     return (
//       <>
//         {this.state.user && <h1>{this.state.user.username}</h1>}
//         {this.state.error}
//       </>
//     );
//   }
// }

// export function User() {
//   const { id } = useParams();
//   return <UserClass id={id} />;
// }
