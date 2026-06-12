import React, {useEffect} from "react";
import {useDispatch} from 'react-redux';

import { HashRouter as Router } from "react-router-dom";

import Header from './Header';
import Body from './Body';
import Footer from './Footer';
import administrators from '../../administrators';

const App = () => {
	
  const dispatch = useDispatch();

  useEffect(() => {
	dispatch(administrators.actions.tryLoginFromServiceToken(
			() => dispatch(administrators.actions.logout())));
  });
	  
  return (
	<div>
    	<Router basename="/ultimateComboRoutes">
			<div>
				<Header/>
      			<Body />
			</div>
    	</Router>
	</div>
  );
};

export default App;