SELECT COUNT(*) AS total
FROM SocialLogin AS sl
JOIN SocialLoginType AS slt ON sl.social_login_type_id = slt.social_login_type_id
WHERE sl.social_login_id = ?
AND slt.provider_name = ?