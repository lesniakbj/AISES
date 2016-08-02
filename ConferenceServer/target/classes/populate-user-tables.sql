INSERT INTO SocialLoginType(social_login_type_id, provider_name)
SELECT 1, 'Facebook'
WHERE NOT EXISTS(SELECT social_login_type_id FROM SocialLoginType WHERE social_login_type_id = 1);

INSERT INTO SocialLoginType(social_login_type_id, provider_name)
SELECT 2, 'Google'
WHERE NOT EXISTS(SELECT social_login_type_id FROM SocialLoginType WHERE social_login_type_id = 2);
