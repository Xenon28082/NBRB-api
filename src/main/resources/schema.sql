create table rate
(
    curId           int          not null,
    date            date         not null,
    curAbbreviation varchar(255) not null,
    curScale        int          not null,
    curName         varchar(255) not null,
    curOfficialRate decimal      not null
);

create table currency
(
    rowId           int auto_increment primary key not null,
    curId           int          not null,
    curParentId     int          not null,
    curCode         varchar(255) not null,
    curAbbreviation varchar(255) not null,
    curName         varchar(255) not null,
    curNameEng      varchar(255) not null,
    curScale        int          not null,
    curPeriodicity  int          not null
);