package com.example.notification

import com.google.auth.oauth2.GoogleCredentials
import java.io.ByteArrayInputStream
import java.io.IOException
import java.nio.charset.StandardCharsets

object AccessToken {

    private val firebaseMessagingScope = "https://www.googleapis.com/auth/firebase.messaging"

    fun getAccessToken(): String? {
        try {
            val jsonString = "{\n" +
                    "  \"type\": \"service_account\",\n" +
                    "  \"project_id\": \"notification-426dd\",\n" +
                    "  \"private_key_id\": \"1d38c6800136e706b6c3da9bf45496606e4327b9\",\n" +
                    "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDT3NHtG/J9vkAb\\njy/KRHJ+8oMlR4LxouWwUFClqptZavphYBdsarH0n6aw0I6W1QZhUTlHnFGT35XF\\nP2OhvQNbFhkFy/KnzUZ/A03t84BmmrIpwXsiyu2W+W4i2U5lkmpAfkMnkDn+B4qt\\nd7W41NSX2h2/+Ch3WIGc/Syv5en1RteBc7AAoDvadpqigVPlbJE47dyuJK9nHFBI\\n/OvdOVg0hP2VKjjCrwQkgM/JzDhGyHEj89BZFyniWYWamsExu1uIKFvmDEr88+H3\\n0D9xfTimRbU2CQy03ftNqQgqDovNeyW3/g05b+jDPwBTjrqJ84ECe7/pzusHq+Om\\nzeinrPpZAgMBAAECggEADVUGbFt/fmB2KA3SCfJV+q1f6Z0HDVXtCpP5ST2T1lQM\\nMsvBWZgav/w6eQK80FrSMpZSCpK4kvvFUOLw7f0HHKimtTXM/LiHHMfMVAKoZDyM\\npOJJMYqv2hny73Q5kdx9fXa8mt0hex4GIO2pNLsG8FHgYKPZBw6dpG+Nq/mFyCKa\\nYKU9v+ZHJMHR6TIboD63gJJ9T8ppaovvnzPLNbg3aBC8XRy5RE3OlUynjRsYFGhm\\nyjnhX9nekglxerLslueMJecC4pdx+bH5xCXWt/Hp3uJ8Jb5A76isqlQli2249vpv\\n20dFxdVS06g6KonPpdYSBHARfejpqOkGyNOKGkqnIQKBgQD3YdWGNJ3LT9yM6eLV\\n8VPDH0ZpCDCJVevIkZCdeJoxXU8dj4V4rirnlTyrfo3Ig9hnRQ01qnHv9bxvBfhg\\nQ3QLPo4amFSMOAYwdqGAmAQwynE2A6dEvYhXopIhHmQ6q8NCXgi9kyIXmdri6vG6\\nHdRxssMuAudv8jq54gbtzt0q4QKBgQDbPjh4O+FaA6PfxSQtz1P0LBsmIbr/OCRs\\nTWYG1jCy+l3M+Y6Y9NiZ2VQmix/SmyhD3ufe2R6pt5oNqAvNAQwheq9tfGhjMadb\\nwe8+nFpau2HYOD8OI2Jv++Pgw4DjqO/Fwodt+0rhwoYSi3Ybz56rV1HXmrroUkFI\\n0I8WO2B2eQKBgQCQ9ahMexqX35VKkWcVnNNLfFEjDOR8JXgD0KCgVeJs2NOaa9Z3\\nb/++MmoZyA/HFtNBOGpULyuSxDmGCUaRChW+3QoiNW8dt1qRHYLnuqyB9q6+SwfC\\nNRnm2GGuvINZCftiMX4HsGcC02o7HBdHy2EIuwq6NINvys50zCjpQxXAoQKBgQDY\\nXbZTCh1ivTbrrl3/C5452ZFNd3pZR2nCrDulQZq79xO9JwgQcYqVgoJmq2RTbyY0\\nnPZN2g5Fxdk14wMKZR84pyAbt+w5AJa+mkzmiF0vL7/nJ9Mqu8b45yjD94zqoB1J\\nGKqY4qfK1zODd7iux546UCmH0r7kadDd8F1VvnF7WQKBgQC9iJgLCKX+Ipd+ik1w\\n1Lt8siH5ySs2xZD9Oh9QtykjmsOdqpbhWhx6KTGmjrnrbvlkR0VC3y93gEK9hTRZ\\nLxnsGpfbh/QI4uHQKsEBiQuOFhVQHgAftlwD9NxLqL3wkTfVreAaVRrp06ptpjlP\\nIY8MZL2+Kgs2qY1G0Hihbh/O5Q==\\n-----END PRIVATE KEY-----\\n\",\n" +
                    "  \"client_email\": \"firebase-adminsdk-rd805@notification-426dd.iam.gserviceaccount.com\",\n" +
                    "  \"client_id\": \"107390052019138757358\",\n" +
                    "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
                    "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
                    "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
                    "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-rd805%40notification-426dd.iam.gserviceaccount.com\",\n" +
                    "  \"universe_domain\": \"googleapis.com\"\n" +
                    "}\n"

            val stream = ByteArrayInputStream(jsonString.toByteArray(StandardCharsets.UTF_8))

            val googleCredential = GoogleCredentials.fromStream(stream)
                .createScoped(arrayListOf(firebaseMessagingScope))

            googleCredential.refresh()

            return googleCredential.accessToken.tokenValue
        } catch (e : IOException) {
            return null
        }

    }
}