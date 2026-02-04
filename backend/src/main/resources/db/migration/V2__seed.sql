INSERT INTO tenants (id, name, timezone, phone_number, provider_type, provider_config_json, created_at)
VALUES ('11111111-1111-1111-1111-111111111111', 'Demo Dental Clinic', 'Asia/Kolkata', '+911234567890', 'TWILIO', '{}', NOW());

INSERT INTO business_profiles (id, tenant_id, hours_json, services_json, policies_text)
VALUES (
    '22222222-2222-2222-2222-222222222222',
    '11111111-1111-1111-1111-111111111111',
    '{"mon":"09:00-18:00","tue":"09:00-18:00"}',
    '["Cleaning","Whitening"]',
    'Please cancel 24 hours in advance.'
);
