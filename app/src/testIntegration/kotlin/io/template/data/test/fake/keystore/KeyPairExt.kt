/*
 * Copyright 2020 Appmattus Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress("DEPRECATION")

package io.template.data.test.fake.keystore

import org.bouncycastle.x509.X509V3CertificateGenerator
import java.math.BigInteger
import java.security.KeyPair
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.Date
import javax.security.auth.x500.X500Principal

@Suppress("MagicNumber")
internal fun KeyPair.toCertificate(): X509Certificate? = X509V3CertificateGenerator()
    .apply {
        val owner = X500Principal("CN=Unknown")
        val from = Date()
        val to = Date(from.time + 365L * 1000L * 24L * 60L * 60L)
        setSerialNumber(BigInteger(64, SecureRandom()))
        setSubjectDN(owner)
        setIssuerDN(owner) // use the same
        setNotBefore(from)
        setNotAfter(to)
        setPublicKey(public)
        setSignatureAlgorithm("SHA256WithRSAEncryption")
    }
    .generate(private, "BC")
