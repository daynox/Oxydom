package fr.oxydom.portadapter.persistence.converter

import fr.oxydom.model.fournisseur.Fournisseur
import org.springframework.beans.factory.annotation.Autowired
import fr.oxydom.portadapter.persistence.entity.EFournisseur
import java.util.*
import java.util.stream.Collectors

class FournisseurConverter {
    @Autowired
    lateinit var agenceConverter : AgenceConverter

    fun fromModelToEntity(fournisseur: Fournisseur): Optional<EFournisseur> {
        if (null == fournisseur) {
            return Optional.empty()
        }

        val entity = EFournisseur(
                fournisseur.idFournisseur,
                fournisseur.numeroSiret,
                fournisseur.nom,
                fournisseur.adresse,
                fournisseur.ville,
                fournisseur.pays,
                fournisseur.email,
                fournisseur.telephone,
                agenceConverter.fromModelsToEntities(fournisseur.agences))

        return Optional.of(entity)
    }

    fun fromEntityToModel(eFournisseur: EFournisseur): Optional<Fournisseur> {
        if (null == eFournisseur) {
            return Optional.empty()
        }

        val model = Fournisseur(
                eFournisseur.idFournisseur,
                eFournisseur.numeroSiret,
                eFournisseur.nom,
                eFournisseur.adresse,
                eFournisseur.ville,
                eFournisseur.pays,
                eFournisseur.adresse,
                eFournisseur.telephone,
                agenceConverter.fromEntitiesToModels(eFournisseur.eAgences))

        return Optional.of(model)
    }

    fun fromModelsToEntities(fournisseurs: List<Fournisseur>): List<EFournisseur> {
        if (fournisseurs.isEmpty()) {
            return emptyList()
        }

        val entities = fournisseurs
                .stream()
                .map(this::fromModelToEntity)
                .filter(Optional<EFournisseur>::isPresent)
                .map(Optional<EFournisseur>::get)
                .collect(Collectors.toList())
        return entities
    }

    fun fromEntitiesToModels(efournisseurs: List<EFournisseur>): List<Fournisseur> {
        if (efournisseurs.isEmpty()) {
            return emptyList()
        }

        val models = efournisseurs
                .stream()
                .map(this::fromEntityToModel)
                .filter(Optional<Fournisseur>::isPresent)
                .map(Optional<Fournisseur>::get)
                .collect(Collectors.toList())

        return models
    }
}