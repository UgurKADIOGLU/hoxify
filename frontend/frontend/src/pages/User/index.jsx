import { Component } from "react";
import { useParams } from "react-router-dom";
import { getUser } from "./api";

export class UserClass extends Component {
  state = {
    user: null,
    apiProgress: false,
    error: null,
  };

  async componentDidMount() {
    this.setState({ apiProgress: true });
    try {
      const response = await getUser(this.props.id);
      this.setState({
        user: response.data,
      });
    } catch (axiosError) {
      this.setState({
        error: axiosError.response.data.message,
      });
    } finally {
      this.setState({ apiProgress: false });
    }
  }

  render() {
    return <> 
    {this.state.user && <h1>{this.state.user.username}</h1>}
    {this.state.error}
    </>;
  }
}

export function User() {
  const { id } = useParams();
  return <UserClass id={id} />;
}