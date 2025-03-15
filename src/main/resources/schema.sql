--
-- Possible schema options to support the 'basic' payloads
--

-- Option A: Entirety of the spec in one table with payload stored as JSON

CREATE TABLE IF NOT EXISTS Specifications (
    id            VARCHAR NOT NULL,
    name          VARCHAR NOT NULL,              -- to display in management GUI and possibly logs to say which specs were applied
    description   VARCHAR NOT NULL,              -- to display in management GUI
    specification JSONB NOT NULL,                -- full spec JSON payload; could just be JSON type, as it's unlikely this will really need to be queried

    CONSTRAINT PrimaryKey
      PRIMARY KEY (id)
);

-- Option B: Spec broken down into individual records

CREATE TABLE IF NOT EXISTS Specifications (
    id            VARCHAR NOT NULL PRIMARY KEY,
    name          VARCHAR NOT NULL,             -- to display in management GUI and possibly logs to say which specs were applied
    description   VARCHAR NOT NULL              -- to display in management GUI
);

CREATE TABLE IF NOT EXISTS SpecificationPredicates (
    specificationId VARCHAR NOT NULL,
    ordinal         VARCHAR NOT NULL,           -- predicate's ordinal within specification
    parameter       VARCHAR NOT NULL,           -- the input property to match against the value
    match           VARCHAR NOT NULL,           -- type of the match to evaluate input against the reference value, e.g. EQUALS, NOT, REGEX
    value           VARCHAR NOT NULL,           -- reference value to compare the input property against

    CONSTRAINT SpecificationPredicatesPrimaryKey
        PRIMARY KEY (specificationId, ordinal),

    CONSTRAINT SpecificationPredicatesSpecificationForeignKey
      FOREIGN KEY (specificationId)
      REFERENCES Specifications(id)
      ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS SpecificationConfigs (
    specificationId VARCHAR NOT NULL,
    fieldName       VARCHAR NOT NULL,           -- configuration field to override
    value           VARCHAR NOT NULL,           -- the value to override the configuration field with

    CONSTRAINT SpecificationConfigsPrimaryKey
        PRIMARY KEY (specificationId, fieldName),

    CONSTRAINT SpecificationConfigsSpecificationForeignKey
      FOREIGN KEY (specificationId)
      REFERENCES Specifications(id)
      ON DELETE CASCADE
);