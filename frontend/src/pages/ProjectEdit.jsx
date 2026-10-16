import { Component } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';

function withRouter(WrappedComponent) {
    return function ComponentWithRouter(props) {
        const navigate = useNavigate();
        const params = useParams();
        return <WrappedComponent {...props} navigate={navigate} params={params} />;
    };
}

class ProjectEdit extends Component {

    emptyItem = {
        projectNumber: '',
        projectName: '',
        startingDate: '',
        endingDate: '',
        active: true
    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        if (this.props.params.id !== 'new') {
            const project = await (await fetch(`/api/projects/${this.props.params.id}`)).json();
            this.setState({ item: project });
        }
    }

    handleChange(event) {
        const target = event.target;
        const value = target.type === 'checkbox' ? target.checked : target.value;
        const name = target.name;
        let item = { ...this.state.item };
        item[name] = value;
        this.setState({ item });
    }

    async handleSubmit(event) {
        event.preventDefault();
        const { item } = this.state;

        await fetch(item.idpi ? '/api/projects/update/' + item.idpi : '/api/projects/add', {
            method: item.idpi ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                projectNumber: item.projectNumber,
                projectName: item.projectName,
                startingDate: item.startingDate || null,
                endingDate: item.endingDate || null,
                active: !!item.active
            }),
        });
        this.props.navigate('/projects');
    }

    render() {
        const { item } = this.state;
        const title = <h2>{item.idpi ? 'Edit Project' : 'Add Project'}</h2>;

        return (
            <div>
                <Container>
                    {title}
                    <Form onSubmit={this.handleSubmit}>
                        <FormGroup>
                            <Label for="projectNumber">Number</Label>
                            <Input type="text" name="projectNumber" id="projectNumber"
                                value={item.projectNumber || ''}
                                onChange={this.handleChange} />
                        </FormGroup>
                        <FormGroup>
                            <Label for="projectName">Name</Label>
                            <Input type="text" name="projectName" id="projectName"
                                value={item.projectName || ''}
                                onChange={this.handleChange} />
                        </FormGroup>
                        <FormGroup>
                            <Label for="startingDate">Starting date</Label>
                            <Input type="date" name="startingDate" id="startingDate"
                                value={item.startingDate || ''}
                                onChange={this.handleChange} />
                        </FormGroup>
                        <FormGroup>
                            <Label for="endingDate">Ending date</Label>
                            <Input type="date" name="endingDate" id="endingDate"
                                value={item.endingDate || ''}
                                onChange={this.handleChange} />
                        </FormGroup>
                        <FormGroup check>
                            <Label check>
                                <Input type="checkbox" name="active" checked={!!item.active}
                                    onChange={this.handleChange} />{' '}
                                Active
                            </Label>
                        </FormGroup>
                        <FormGroup>
                            <Button color="primary" type="submit">Save</Button>{' '}
                            <Button color="secondary" tag={Link} to="/projects">Cancel</Button>
                        </FormGroup>
                    </Form>
                </Container>
            </div>
        );
    }
}

export default withRouter(ProjectEdit);