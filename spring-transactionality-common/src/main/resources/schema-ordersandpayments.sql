CREATE TABLE ORDERS
(
    ID                 VARCHAR(255)  NOT NULL,

    CONSTRAINT PK_ORDERS PRIMARY KEY (ID)
);

CREATE TABLE PAYMENTS
(
    ID                 VARCHAR(255)  NOT NULL,

    CONSTRAINT PK_PAYMENTS PRIMARY KEY (ID)
);