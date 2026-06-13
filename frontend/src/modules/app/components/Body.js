import React from "react";
import {useSelector} from "react-redux";
import { Route, Switch, withRouter } from "react-router-dom";

import AppGlobalComponents from "./AppGlobalComponents";
import Home from "./Home";
import administrators, {Login, SignUp, SignUpAdmin, UpdateProfile, ChangePassword, Logout} from "../../administrators";

const Body = () => {
	
	const loggedIn = useSelector(administrators.selectors.isLoggedIn);
	const admin = useSelector(administrators.selectors.getAdmin);
	
  	return (
		<div className="container">
			<br/>
			<AppGlobalComponents/>
    		<Switch>
      			<Route exact path="/" component={Home} />
				{loggedIn && <Route exact path="/administrators/update-profile"><UpdateProfile/></Route>}
            	{loggedIn && <Route exact path="/administrators/change-password"><ChangePassword/></Route>}
            	{loggedIn && <Route exact path="/administrators/logout"><Logout/></Route>}
            	{!loggedIn && <Route exact path="/administrators/login"><Login/></Route>}
            	{!loggedIn && <Route exact path="/administrators/signup"><SignUp/></Route>}
				<Route><Home/></Route>
    		</Switch>
		</div>
  	);
};

export default withRouter(Body);