import {
    fetchConfig,
    appFetch,
}from "./appFetch";

export const uploadSmashCharacter = (
    name,
    description,
    weight,
    gravity,
    render,
    onSuccess,
    onErrors
) => 
    appFetch(
        "/smashCharacters/uploadCharacter", fetchConfig("POST", {name, description, 
            weight, gravity, render}),  
            onSuccess,
            onErrors
        );

export const updateSmashCharacter = (
    id,
    name,
    description,
    weight,
    gravity,
    render,
    onSuccess,
    onErrors
) =>
    appFetch(
        `/smashCharacters/${id}/updateCharacter`, fetchConfig("PUT", {name, description,
            weight, gravity, render}),
            onSuccess,
            onErrors
        );

export const showSmashCharacterDetails = (
    id,
    onSuccess
) =>
    appFetch(
        `/smashCharacters/${id}`, fetchConfig("GET"),
        onSuccess
    );

export const showSmashCharacters = (
    onSuccess
) =>
    appFetch(
        "/smashCharacters", fetchConfig("GET"),
        onSuccess
    );

export const filterByName = (
    {name},
    onSuccess
) => 
    appFetch(
        `/smashCharacters/filterCharacters?name=${name}`, fetchConfig("GET"),
        onSuccess
    );
    
