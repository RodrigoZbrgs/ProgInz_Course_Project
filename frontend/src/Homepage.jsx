import React, { Component } from 'react';
import './App.css';
import NavBar from './AppNavBar';
import { Link } from 'react-router-dom';
import { Button, Container } from 'reactstrap';

class Home extends Component {
    render() {
        return (
            <div>
                <NavBar/>
                <Container fluid>
                    <Button color="link"><Link to="/projects">Projects</Link></Button>
                    <Button color="link"><Link to="/projects">Users</Link></Button>
                    <Button color="link"><Link to="/projects">Work Description</Link></Button>
                    <Button color="link"><Link to="/projects">KPI Realisation</Link></Button>
                </Container>
            </div>
        );
    }
}
export default Home;