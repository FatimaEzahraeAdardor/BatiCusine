CREATE TYPE project_status AS ENUM ('INPROGRESS', 'FINISHED', 'CANCELLED');
CREATE TABLE clients (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         address VARCHAR(255),
                         phone VARCHAR(50),
                         isProfessional BOOLEAN
);

CREATE TABLE projects (
                          id SERIAL PRIMARY KEY,
                          projectName VARCHAR(255) NOT NULL,
                          profitMargin DOUBLE PRECISION,
                          totalCost DOUBLE PRECISION,
                          projectStatus project_status,
                          client_id INTEGER REFERENCES clients(id) ON DELETE SET NULL
);

CREATE TABLE components (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(100) NOT NULL,
                            componentType VARCHAR(255),
                            vatRate DOUBLE PRECISION NOT NULL,
                            project_id INTEGER REFERENCES projects(id) ON DELETE CASCADE
);

CREATE TABLE materials (
                           id SERIAL PRIMARY KEY,
                           unitCost DOUBLE PRECISION NOT NULL,
                           quantity DOUBLE PRECISION NOT NULL,
                           transportCost DOUBLE PRECISION NOT NULL,
                           qualityCoefficient DOUBLE PRECISION NOT NULL

)
 inherits(components);
CREATE TABLE labor (
                       id SERIAL PRIMARY KEY ,
                       hourlyRate DOUBLE PRECISION NOT NULL,
                       hoursWorked DOUBLE PRECISION NOT NULL,
                       workerProductivity DOUBLE PRECISION NOT NULL
)
    inherits(components);
;

CREATE TABLE quotes(
                       id SERIAL PRIMARY KEY,
                       estimatedAmount DOUBLE PRECISION NOT NULL,
                       issueDate DATE NOT NULL,
                       validityDate DATE NOT NULL,
                       isaccepted BOOLEAN DEFAULT FALSE,
                       project_id INTEGER REFERENCES projects (id) ON DELETE CASCADE
);