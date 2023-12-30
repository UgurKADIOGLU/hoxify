import SingUp from "../pages/SingUp/Index.jsx";
import Home from "../pages/Home/index.jsx";
import { createBrowserRouter } from "react-router-dom";
import App from "../App.jsx"
import Activation from "../pages/Activation/index.jsx";



export default createBrowserRouter([
{
  path:"/",
  Component:App,
  children:[
    {
      path: "/",
      index:true,
      Component: Home,
      
    },
    {
      path: "/singup",
      Component: SingUp,
    },
    {
      path: "/activation/:token",
      Component: Activation,
    },
  ]
}
]);
