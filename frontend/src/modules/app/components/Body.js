import React from "react";
import {useSelector} from "react-redux";
import { Route, Switch, withRouter } from "react-router-dom";

import AppGlobalComponents from "./AppGlobalComponents";
import Home from "./Home";
import administrators, {Login, SignUp, SignUpAdmin, UpdateProfile, ChangePassword, Logout} from "../../administrators";
import { CharacterUploadCompleted, ShowSmashCharacterDetails, UpdateSmashCharacterData, UploadSmashCharacter } from "../../smashCharacters";
import { AddCombo, AddComboCompleted, EditCombo } from "../../combos";
import ShowCharacterCombos from "../../combos/components/ShowCharacterCombos";

const Body = () => {
	
	const loggedIn = useSelector(administrators.selectors.isLoggedIn);
	const admin = useSelector(administrators.selectors.getAdmin);
	
  	return (
		<div className="container">
			<br/>
			<AppGlobalComponents/>
    		<Switch>
      			<Route exact path="/" component={Home} />
				<Route exact path="/smashCharacters/smashCharacterDetails/:id" component={ShowSmashCharacterDetails}/>
				<Route exact path="/combos/:id/showCharacterCombos" component={ShowCharacterCombos}/>
				{loggedIn && <Route exact path="/smashCharacters/:id/updateSmashCharacter"><UpdateSmashCharacterData/></Route>}
				{loggedIn && <Route exact path="/administrators/update-profile"><UpdateProfile/></Route>}
            	{loggedIn && <Route exact path="/administrators/change-password"><ChangePassword/></Route>}
            	{loggedIn && <Route exact path="/administrators/logout"><Logout/></Route>}
            	{!loggedIn && <Route exact path="/administrators/login"><Login/></Route>}
            	{!loggedIn && <Route exact path="/administrators/signup"><SignUp/></Route>}
				{loggedIn && <Route exact path ="/smashCharacters/uploadCharacter"><UploadSmashCharacter/></Route>}
				{loggedIn && <Route exact path ="/smashCharacters/characterUploadCompleted"><CharacterUploadCompleted/></Route>}
				{loggedIn && <Route exact path ="/smashCharacters/updateCharacter"><UpdateSmashCharacterData/></Route>}
				{loggedIn && <Route exact path ="/combos/:id/addCombo"><AddCombo/></Route>}
				{loggedIn && <Route exact path ="/combos/:id/editCombo"><EditCombo/></Route>}
				{loggedIn && <Route exact path ="/combos/comboAdditionCompleted"><AddComboCompleted/></Route>}
				<Route><Home/></Route>
    		</Switch>
		</div>
  	);
};

export default withRouter(Body);