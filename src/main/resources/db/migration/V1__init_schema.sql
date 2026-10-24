-- Initial schema creation

CREATE TABLE IF NOT EXISTS students (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    date_of_birth VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS transport_routes (
    id BIGSERIAL PRIMARY KEY,
    route_name VARCHAR(255),
    start_location VARCHAR(255),
    end_location VARCHAR(255),
    vehicle_number VARCHAR(100),
    driver_name VARCHAR(255),
    driver_contact VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS notices (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255),
    content TEXT,
    author VARCHAR(255),
    published_at TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS events (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255),
    description TEXT,
    event_date DATE,
    location VARCHAR(255),
    organizer VARCHAR(255),
    created_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS messages (
    id BIGSERIAL PRIMARY KEY,
    sender_name VARCHAR(255),
    receiver_name VARCHAR(255),
    subject VARCHAR(255),
    body TEXT,
    is_read BOOLEAN DEFAULT FALSE,
    sent_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS leave_applications (
    id BIGSERIAL PRIMARY KEY,
    applicant_name VARCHAR(255),
    reason TEXT,
    start_date DATE,
    end_date DATE,
    status VARCHAR(20) DEFAULT 'PENDING',
    applied_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS notifications (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255),
    message TEXT,
    recipient_type VARCHAR(100),
    recipient_id BIGINT,
    is_read BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS audit_logs (
    id BIGSERIAL PRIMARY KEY,
    action VARCHAR(255),
    entity_type VARCHAR(255),
    entity_id BIGINT,
    performed_by VARCHAR(255),
    details TEXT,
    performed_at TIMESTAMP
);
