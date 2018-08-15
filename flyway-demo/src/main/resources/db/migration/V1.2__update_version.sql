CREATE TABLE IF NOT EXISTS `afftest4` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NOT NULL DEFAULT '0',
  `name` varchar(255) NOT NULL DEFAULT '',
  `logo_url` varchar(255) NOT NULL DEFAULT '',
  `description` text,
  `owner_role_id` bigint(20) NOT NULL,
  `alliance_id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `public_type` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `auto_shard_key_alliance_id_0` (`alliance_id`)
) ENGINE=InnoDB AUTO_INCREMENT=591022 DEFAULT CHARSET=utf8;
