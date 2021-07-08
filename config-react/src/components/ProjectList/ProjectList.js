import React from 'react'
import { Projects } from '../Projects/Projects'

function ProjectList() {
    const projectNames = [
          
        {
            id: 1,
            name: 'Liverpool',
        },
        {
            id: 2,
            name: 'Suburbia',
        },
        {
            id: 3,
            name: 'Williams Sonama'
        },

    ]
    const projectNamesList = projectNames.map( (projectname) => (
        <Projects key={projectname.id} projectname={projectname}></Projects>
    ))
    return (
        <div>
            {projectNamesList}
        </div>
    )
}

export default ProjectList
