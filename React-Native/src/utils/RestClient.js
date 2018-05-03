const BASE_URL = 'https://bridge-worldcup.herokuapp.com/'

export async function getGrupos(ano) {
  try {
    const url = `${BASE_URL}api/v1/edicoes/${ano}/grupos`

    let response = await fetch(url)
    let responseJSON = await response.json()
    return { error: null, grupos: responseJSON }

  } catch (error) {
    return { error: error, grupos: [] }
  }
}