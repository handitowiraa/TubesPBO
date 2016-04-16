create table penyedia (
	id_penyedia int primary key,
	nama varchar(30) not null,
	username varchar(20) not null,
	password varchar(20) not null
);

create table petugas (
	id_petugas int primary key,
	nama varchar(30) not null,
	username varchar(20) not null,
	password varchar(20) not null
);

create table gudang (
	id_gudang int primary key,
	nama_gudang varchar(20)
);

create table barang_penyedia (
	id_penyedia int,
	id_barang int primary key,
	nama_barang varchar(20),
	jumlah int,
	kondisi varchar(20),
	constraint fkbp foreign key (id_penyedia) references penyedia (id_penyedia)
);

create table barang_gudang (
	id_gudang int,
	id_barang int primary key,
	nama_barang varchar(20),
	jumlah int,
	kondisi varchar(20),
	constraint fkbg foreign key (id_gudang) references gudang (id_gudang)
);