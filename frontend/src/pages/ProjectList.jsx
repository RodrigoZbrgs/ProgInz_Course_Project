import React, {Component} from "react";
import { Button, ButtonGroup, Container, Table } from 'reactstrap'
import { Link } from "react-router-dom";
import NavBar from "../AppNavBar";

class ProjectList extends Component {

    constructor(props) {
        super(props);
        this.state = {projects : []}
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        fetch('api/projects')
        .then(response => response.json())
        .then(data => this.setState({projects:data}));
    }

    async remove(id) {
    await fetch(`/api/projects/delete/${id}`, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    }).then(() => {
        let updatedProjects = [...this.state.projects].filter(i => i.idpi !== id);
        this.setState({projects: updatedProjects});
    });
}

    render() {
    const {projects} = this.state;

    const projectList = projects.map(project => {
        return <tr key={project.idpi}>
            <td style={{whiteSpace: 'nowrap'}}>{project.projectNumber}</td>
            <td>{project.projectName}</td>
            <td>{project.startingDate}</td>
            <td>{project.endingDate}</td>
            <td>{project.active ? 'Yes' : 'No'}</td>
            <td>
                <ButtonGroup>
                    <Button size="sm" color="primary" tag={Link} to={"/projects/" + project.idpi}>Edit</Button>
                    <Button size="sm" color="danger" onClick={() => this.remove(project.idpi)}>Delete</Button>
                </ButtonGroup>
            </td>
        </tr>
    });

    return (
        <div>
            <NavBar />
            <Container fluid>
                <div className="float-right">
                    <Button color="success" tag={Link} to="/projects/new">Add Project</Button>
                </div>
                <h3>Projects</h3>
                <Table className="mt-4">
                    <thead>
                    <tr>
                        <th width="20%">Number</th>
                        <th width="20%">Name</th>
                        <th width="20%">Start</th>
                        <th width="20%">End</th>
                        <th width="10%">Active</th> 
                        <th width="10%">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {projectList}
                    </tbody>
                </Table>
            </Container>
        </div>
    );
}

}

export default ProjectList;