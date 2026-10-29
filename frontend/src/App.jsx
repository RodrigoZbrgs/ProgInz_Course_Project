import React, { Component } from 'react';
import './App.css';
import Homepage from './Homepage';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import ProjectList from './pages/ProjectList';
import ProjectEdit from './pages/ProjectEdit';

class App extends Component {
    render() {
        return (
            <Router>
                <Routes>
                    <Route path="/" element={<Homepage />} />
                    <Route path="/projects" element={<ProjectList />} />
                    <Route path="/projects/:id" element={<ProjectEdit />} />
                </Routes>
            </Router>
        );
    }
}

export default App;
