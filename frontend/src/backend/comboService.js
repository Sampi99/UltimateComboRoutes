import {
    fetchConfig,
    appFetch,
}from "./appFetch";

export const addCombo = (
    characterId,
    secuence,
    difficulty,
    totalDamage,
    demo,
    onSuccess,
    onErrors
) => 
    appFetch(
        `/combos/${characterId}/addCombo`, fetchConfig("POST", {characterId, secuence, 
            difficulty, totalDamage, demo}),  
            onSuccess,
            onErrors
        );

export const editCombo = (
    id,
    secuence,
    difficulty,
    totalDamage,
    demo,
    onSuccess,
    onErrors
) =>
    appFetch(
        `/combos/${id}/editCombo`, fetchConfig("PUT", {secuence, difficulty,
            totalDamage, demo}),
            onSuccess,
            onErrors
        );

export const showCharacterCombos = (
    id,
    onSuccess
) =>
    appFetch(
        `/combos/${id}`, fetchConfig("GET"),
        onSuccess
    );

export const filterByDifficulty = (
    {difficulty, characterId},
    onSuccess
) => 
    appFetch(
        `/combos/${characterId}/filterByDifficulty?difficulty=${difficulty}`, fetchConfig("GET"),
        onSuccess
    );
    