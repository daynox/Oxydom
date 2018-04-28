package portadapter.persistence.entity

import java.time.ZonedDateTime

data class EMaquette (
        var idMaquette : Int,
        var libelleMaquette : String,
        var codeMaquette : String,
        var numeroDevis : String,
        var dateCreationDevis : ZonedDateTime,
        var dateLimiteValidite : ZonedDateTime,
        var dureeTravauxEstimee : Int,
        var commentaire : String,
        var status : String,
        var einstanceDeModules: List<EInstanceDeModule>,
        var etva : ETva,
        var eprojet : EProjet
){

}